package com.junga.cupofsoju


//Copyright 2019 Hanjanha Jo
//
//
//Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
//file except in compliance with the License. You may obtain a copy of the License at
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software distributed
//under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
//express or implied. See the License for the specific language governing permissions and limitations under the License.
//
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
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
import kotlinx.android.synthetic.main.activity_select_type.*
import kotlinx.android.synthetic.main.activity_signup_group.*
import kotlinx.android.synthetic.main.activity_signup_group.signup_btn
import org.jetbrains.anko.startActivity

class SignUpGroupActivity : AppCompatActivity(),View.OnClickListener {

    private val TAG = "SignUpGroupActivity"

    private lateinit var email : String;
    private lateinit var groupName : String;
    private lateinit var password : String;
    private lateinit var phone : String;
    private lateinit var birth : String;


    //Firebase Auth, firestore
    lateinit  var mAuth : FirebaseAuth;
    lateinit var db: FirebaseFirestore;




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_group)

        signup_btn.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.signup_btn -> {
                email = group_email.text.toString()
                groupName = group_name.text.toString()
                password = group_password.text.toString()
                phone = group_call.text.toString()
                birth = group_birth.text.toString()
                signup()
            }
        }
    }


    private fun signup(){
        //If all nessasary field hasn't been filled, return.
        if(!checkAllFilled()) return

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
                    Snackbar.make(layout,"최소 6자 이상의 비밀번호를 설정해 주세요", Snackbar.LENGTH_SHORT).show()

                }

                if (it is FirebaseAuthUserCollisionException) {
                    Log.d(TAG, "FirebaseAuthUserCollision Exception occured : " + it.getLocalizedMessage())
                    Snackbar.make(layout,"이미 가입된 이메일 입니다.", Snackbar.LENGTH_SHORT).show()

                }
            })

    }


    private fun checkAllFilled() : Boolean {
        if(email.isEmpty()||password.isEmpty()||groupName.isEmpty()||phone.isEmpty()||birth.isEmpty()){
            Snackbar.make(layout,"필수 영역을 채워주세요", Snackbar.LENGTH_SHORT).show()
            return false
        }else{
            return true;
        }
    }

    private fun updateDatabase(email : String) {
        //TODO need to fix location
        val userData:com.junga.cupofsoju.model.UserData = com.junga.cupofsoju.model.UserData(email,password,groupName,null,phone,0,
            ProjectValue.Companion.GROUP,0,0)
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
