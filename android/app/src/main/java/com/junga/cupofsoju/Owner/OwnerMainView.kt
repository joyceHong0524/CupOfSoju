package com.junga.cupofsoju.Owner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.junga.cupofsoju.R
import com.junga.cupofsoju.model.StoreData
import com.junga.cupofsoju.model.UserData
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_owner_main.*
import kotlinx.android.synthetic.main.activity_owner_main.drawer


class OwnerMainView : AppCompatActivity(), View.OnClickListener {
    val TAG = "MainActivity"

    lateinit var tvCount: TextView
    lateinit var tvCashback: TextView

    lateinit var navDrawer: Drawer;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner_main)
        val db = FirebaseFirestore.getInstance()

        buildDrawer()

        drawer.setOnClickListener(this)


        tvCount = findViewById(R.id.owner_main_count)
        tvCashback = findViewById(R.id.owner_main_cashback)

        val email = FirebaseAuth.getInstance().currentUser!!.email

//        db.collection("Store")
//                .get()
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        Log.d("size :::::",task.result!!.size().toString())
//                        for (document in task.result!!) {
//                            val (email1, _, _, _, _, _, _, _, _, _, _, soju_count) = document.toObject(StoreData::class.java)
//                            if (email1 == email) {
//                                Log.d("a","Ddfd")
//
//                                break
//                            }
//
//                        }
//                    }
//                }
//

        db.collection("Store")
            .whereEqualTo("email", email) // <-- This line
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val querySnapshot = task.result
                    val doc = querySnapshot!!.documents.get(0)
                    val oldUser = doc.toObject(StoreData::class.java)
                    var string = oldUser!!.soju_count.toString()+" 병"
                    tvCount.setText(string)
                    tvCashback.setText("${oldUser!!.soju_count * 800}원")

                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }

    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.drawer -> navDrawer.openDrawer()
        }
    }


    private fun buildDrawer() {

        val item1 = PrimaryDrawerItem().withIdentifier(1).withName("회원정보 수정")
        val item2 = PrimaryDrawerItem().withIdentifier(2).withName("로그아웃")
        val item3 = PrimaryDrawerItem().withIdentifier(3).withName("가맹 탈퇴")



        //create Profile part

        val accountHeaderResult = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.grey)
                .addProfiles(
                        ProfileDrawerItem().withName("LianJi님").withEmail("soja0524@gmail.com").withIcon(R.drawable.dummy_profile_girl)
                )
                .build()


        //create the drawer and remember the `Drawer` result object
        //TODO need to add header
        navDrawer = DrawerBuilder()
                .withActivity(this)
//                .withAccountHeader(accountHeaderResult)
                .addDrawerItems(
                        item1,
                        DividerDrawerItem(),
                        item2,
                        DividerDrawerItem(),
                        item3,
                        DividerDrawerItem()
                )
                .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                    override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean {
                        // do something with the clicked item :D
                        Log.d(TAG, position.toString())
                        return false
                    }
                })
                .withSelectedItem(-1) //This is for making none of items are not selected when it is initialized.
                .build()

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            val scanResult: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            val re = scanResult.contents;
            Toast.makeText(this, "This is result!!!!!!!" + re, Toast.LENGTH_LONG).show()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}


