package com.junga.cupofsoju

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager

import com.google.android.material.tabs.TabLayout
import com.junga.cupofsoju.Adapter.PageAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_introduce.*
import org.jetbrains.anko.startActivity

import java.util.ArrayList

class IntroduceActivity : AppCompatActivity(),View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.intro_login -> startActivity<LogInActivity>()
            R.id.intro_signUp -> startActivity<SignUpActivity>()
        }
    }

    internal var temp: List<Drawable>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduce)

        val drawables = ArrayList<Drawable>()
        drawables.add(ContextCompat.getDrawable(this, R.drawable.intro1)!!)
        drawables.add(ContextCompat.getDrawable(this, R.drawable.intro2)!!)
        drawables.add(ContextCompat.getDrawable(this, R.drawable.intro3)!!)
        drawables.add(ContextCompat.getDrawable(this, R.drawable.intro4)!!)

        val dotsIndicator = findViewById<View>(R.id.worm_dots_indicator) as WormDotsIndicator
        val viewPager = findViewById<View>(R.id.photos_viewpager) as ViewPager
        val adapter = PageAdapter(this)
        viewPager.adapter = adapter
        dotsIndicator.setViewPager(viewPager)

        intro_login.setOnClickListener(this)
        intro_signUp.setOnClickListener(this)

    }
}


