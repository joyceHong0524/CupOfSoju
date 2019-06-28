package com.junga.cupofsoju

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
           startActivity<IntroduceActivity>()

            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            finish()

        },2000)
    }
}
