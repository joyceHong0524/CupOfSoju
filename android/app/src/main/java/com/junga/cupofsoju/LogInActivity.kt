package com.junga.cupofsoju

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_log_in.*
import org.jetbrains.anko.startActivity
import com.google.firebase.auth.FirebaseUser
import androidx.annotation.NonNull
import android.graphics.Color.DKGRAY
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider


class LogInActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "LogInActivity"


    lateinit  var mAuth : FirebaseAuth;
    lateinit var mAuthListener : FirebaseAuth.AuthStateListener;
    lateinit var googleSignInClient: GoogleSignInClient;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        button_google.setOnClickListener(this)
        button_login.setOnClickListener(this)
        textView_signup.setOnClickListener(this)

        mAuth = FirebaseAuth.getInstance()

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.uid)
            } else {
                Log.d(TAG, "onAuthStateChanged:signed_out")
            }
            // updateUI(user)
        }

    }


    override fun onClick(p0: View?) {

        when(p0!!.id){

            R.id.button_google -> googleLogin()
            R.id.button_login -> emailLogin()
            R.id.textView_signup -> {
                startActivity<SignUpActivity>()
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            }

        }
    }



    private fun googleLogin(){
        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id)) //Since R.stinrg.default_web_client_id keep not being resolved, I'm just using string value.
            .requestEmail()
            .build();

        googleSignInClient = GoogleSignIn.getClient(this,gso)

        val signInIntent =googleSignInClient.signInIntent
        startActivityForResult(signInIntent,100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==100){
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if(result.isSuccess){
                //구글 로그인이 성공했을 경우
                val account =result.signInAccount
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
            }
        }
    }


    private fun emailLogin(){

        val email = input_email.text.toString()
        val password = input_password.text.toString()

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
            this
        ) { task ->
            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful)
            if (!task.isSuccessful) {
                Log.d(TAG,"tas k was not successful")
            } else {
                Log.d(TAG, "signInWithEmail:onComplete:" + "LOGED IN!!")
                val user = mAuth.currentUser
                Log.d(TAG,"current user Id : " + user!!.email)
                startActivity<MainActivity>()
            }
        }


    }
}
