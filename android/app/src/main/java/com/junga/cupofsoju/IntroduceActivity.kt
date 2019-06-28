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
            R.id.intro_login -> {
                startActivity<LogInActivity>()
                finish()
            }
            R.id.intro_signUp -> {
                startActivity<SignUpActivity>()
                finish()
            }
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


