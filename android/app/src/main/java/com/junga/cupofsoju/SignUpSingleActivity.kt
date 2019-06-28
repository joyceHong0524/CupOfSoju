package com.junga.cupofsoju

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.service.autofill.UserData
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.junga.cupofsoju.model.ProjectValue
import kotlinx.android.synthetic.main.activity_signup_single.*
import org.jetbrains.anko.startActivity

class SignUpSingleActivity : AppCompatActivity(), View.OnClickListener {


    private val TAG = "SingUpSingleActivity"

    private lateinit var email : String;
    private lateinit var nickname : String;
    private lateinit var password : String;
    private lateinit var name : String;
    private lateinit var phone : String;
    private lateinit var birth : String;


    //Firebase Auth, firestore
    lateinit  var mAuth : FirebaseAuth;
    lateinit var db: FirebaseFirestore;


    //SharedPreference for saving uid




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_single)

        signup_btn.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.signup_btn -> {
                email = single_email.text.toString()
                nickname = single_nickname.text.toString()
                password = single_password.text.toString()
                name = single_name.text.toString()
                phone = single_call.text.toString()
                birth = single_birth.text.toString()
                signup()

            }
        }
    }


    private fun signup(){
        //If all nessasary field hasn't been filled, return.
        if(!checkAllFilled()) return

        Log.d("d","checked")

        //If all filled, create new user on Firebase.
        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this, OnCompleteListener {
                if(it.isSuccessful()){
                    Log.d(TAG,"successfully signed up!")
                    updateDatabase(email) //Auth and UserDatabase is connected through email

                }
            })
            .addOnFailureListener(this, OnFailureListener {
                if (it is FirebaseAuthWeakPasswordException) {
                    Log.d(TAG, "FirebaseAuthWeakPassword Exception occured : " + it.getLocalizedMessage())
                    Snackbar.make(layout,"최소 6자 이상의 비밀번호를 설정해 주세요",Snackbar.LENGTH_SHORT).show()

                }

                if (it is FirebaseAuthUserCollisionException) {
                    Log.d(TAG, "FirebaseAuthUserCollision Exception occured : " + it.getLocalizedMessage())
                    Snackbar.make(layout,"이미 가입된 이메일 입니다.",Snackbar.LENGTH_SHORT).show()

                }
            })





    }


    private fun checkAllFilled() : Boolean {
        if(email.isEmpty()||nickname.isEmpty()||password.isEmpty()||name.isEmpty()||phone.isEmpty()||birth.isEmpty()){
            Snackbar.make(layout,"필수 영역을 채워주세요",Snackbar.LENGTH_SHORT).show()
            return false
        }else{
            return true;
        }
    }

    private fun updateDatabase(email : String) {
        //TODO need to fix location
        val userData:com.junga.cupofsoju.model.UserData = com.junga.cupofsoju.model.UserData(email,password,name,null,phone,0,ProjectValue.Companion.SINGLE,0,0)
        db.collection("User")
            .add(userData)
            .addOnSuccessListener(OnSuccessListener<DocumentReference>(){
                Log.d(TAG, "DocumentSnapshot written with ID: " + it.getId())
                //여기서 userId는 user doc의 자동생성된 값을 말한다.
                val userId = it.getId()
                Log.d(TAG,"USER ID ------------->"+userId)
                mHandler.sendEmptyMessage(0)

            }).addOnFailureListener {
                Log.w(TAG, "Error adding document", it)
            }
    }


    private val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {

        override fun handleMessage(msg: Message?) {
            when(msg!!.what){
                0 -> {
                    startActivity<LogInActivity>()
                    finish()
                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity<SplashActivity>()
    }

}
