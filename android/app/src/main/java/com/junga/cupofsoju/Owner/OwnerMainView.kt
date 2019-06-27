package com.junga.cupofsoju.Owner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.junga.cupofsoju.CustomScannerActivity
import com.junga.cupofsoju.R
import com.junga.cupofsoju.model.StoreData
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.jvm.internal.markers.KMutableMap

import java.util.ArrayList

class OwnerMainView : AppCompatActivity(), View.OnClickListener {
    val TAG = "MainActivity"

    lateinit var tvCount: TextView
    lateinit var tvCashback: TextView

    lateinit var navDrawer: Drawer;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = FirebaseFirestore.getInstance()

        buildDrawer()

        drawer.setOnClickListener(this)


        tvCount = findViewById(R.id.owner_main_count)
        tvCashback = findViewById(R.id.owner_main_cashback)

        val email = FirebaseAuth.getInstance().currentUser!!.email

        db.collection("Store")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            val (email1, _, _, _, _, _, _, _, _, _, _, soju_count) = document.toObject(StoreData::class.java)
                            if (email1 == email) {
                                tvCount.setText(soju_count)
                                tvCashback.setText(soju_count * 800)
                                break
                            }

                        }
                    }
                }


    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.drawer -> navDrawer.openDrawer()
            R.id.qrcode -> getQRcode()
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
//            .withAccountHeader(R.layout.activity_main)
//            .withOnAccountHeaderListener(object : AccountHeader.OnAccountHeaderListener {
//                override fun onProfileChanged(view: View?, profile: IProfile<*>, current: Boolean): Boolean {
//                    return false
//                }
//            })
                .build()


        //create the drawer and remember the `Drawer` result object
        //TODO need to add header
        navDrawer = DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(accountHeaderResult)
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

    private fun getQRcode() {
        Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show()

        val integrator = IntentIntegrator(this)
        integrator.setBeepEnabled(false)
        integrator.captureActivity = CustomScannerActivity::class.java
        integrator.initiateScan()

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


