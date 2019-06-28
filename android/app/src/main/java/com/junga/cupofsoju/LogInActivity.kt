package com.junga.cupofsoju

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_log_in.*
import org.jetbrains.anko.startActivity
import androidx.annotation.NonNull
import android.graphics.Color.DKGRAY
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.junga.cupofsoju.Owner.OwnerMainView
import com.junga.cupofsoju.model.UserData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup_single.*


class LogInActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "LogInActivity"


    lateinit  var mAuth : FirebaseAuth;
    lateinit var mAuthListener : FirebaseAuth.AuthStateListener;
    lateinit var googleSignInClient: GoogleSignInClient;
    lateinit var pref: SharedPreferences;
    lateinit var editor :SharedPreferences.Editor
    lateinit var layout : LinearLayout

    lateinit var db : FirebaseFirestore;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

//        button_google.setOnClickListener(this)
        button_login.setOnClickListener(this)
        textView_signup.setOnClickListener(this)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        layout = findViewById(R.id.layout)
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.uid)
            } else {
                Log.d(TAG, "onAuthStateChanged:signed_out")
            }
            // updateUI(user)
        }

        pref = getSharedPreferences("user", Context.MODE_PRIVATE)

    }


    override fun onClick(p0: View?) {

        when(p0!!.id){

//            R.id.button_google -> googleLogin()
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
                saveEmailInSharePrferences(account!!.email)
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
                Log.d(TAG,"task was not successful")
            } else {
                Log.d(TAG, "signInWithEmail:onComplete:" + "LOGED IN!!")
                val user = mAuth.currentUser
                Log.d(TAG,"current user Id : " + user!!.email)
                saveEmailInSharePrferences(user.email)
                defineWhereTogo(user)
            }
        }.addOnFailureListener {
                Snackbar.make(layout,"입력 정보를 다시 확인해주세요", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun saveEmailInSharePrferences(email :String?){
        editor = pref.edit()
        editor.putString("email",email)
        editor.apply()
    }

    private fun defineWhereTogo(user : FirebaseUser ){

           db.collection("User")
               .whereEqualTo("email", user.email) // <-- This line
               .get()
               .addOnCompleteListener { task ->
                   if (task.isSuccessful) {

                       val querySnapshot = task.result

                       if(querySnapshot!!.documents.size != 0){
                           val doc = querySnapshot!!.documents.get(0)
                           val oldUser = doc.toObject(UserData::class.java)
                           startActivity<MainActivity>()
                       }else{
                           db.collection("Store")
                               .whereEqualTo("email", user.email) // <-- This line
                               .get()
                               .addOnCompleteListener { task ->
                                   if (task.isSuccessful) {
                                       val querySnapshot = task.result
                                       val doc = querySnapshot!!.documents.get(0)
                                       val oldUser = doc.toObject(com.junga.cupofsoju.model.UserData::class.java)
                                       startActivity<com.junga.cupofsoju.Owner.OwnerMainView>()
                                   } else {
                                       android.util.Log.d(TAG, "Error getting documents: ", task.exception)
                                   }
                               }
                       }



                   } else {
                       Log.d(TAG, "Error getting documents: ", task.exception)
                   }
               }


       }

    override fun onBackPressed() {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("종료 하시겠습니까??")
            .setCancelable(false)
            .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                finish()
                dialogInterface.cancel()
            })
            .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
        val alert = dialog.create()
        alert.show()

    }
    }

