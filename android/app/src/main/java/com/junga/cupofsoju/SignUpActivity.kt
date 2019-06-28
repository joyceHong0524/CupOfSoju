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
