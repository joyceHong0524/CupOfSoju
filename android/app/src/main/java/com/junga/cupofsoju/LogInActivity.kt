package com.junga.cupofsoju

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_log_in.*
import org.jetbrains.anko.startActivity

class LogInActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        button_google.setOnClickListener(this)
        button_login.setOnClickListener(this)
        textView_signup.setOnClickListener(this)

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

    }

    private fun emailLogin(){

    }
}
