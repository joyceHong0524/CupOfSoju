package com.junga.cupofsoju

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_select_type.*
import org.jetbrains.anko.startActivity

class SignUpActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_type)

        person.setOnClickListener(this)
        group.setOnClickListener(this)
        owner.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.person -> {
                startActivity<SignUpSingleActivity>()
                finish()
            }
            R.id.group -> {
                startActivity<SignUpGroupActivity>()
            }
            R.id.owner -> {
                startActivity<SignUpStoreActivity>()
                finish()
            }
        }
    }

    override fun onBackPressed() {

        startActivity<SplashActivity>()
    }
}
