package com.junga.cupofsoju

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IProfile
import kotlinx.android.synthetic.main.activity_main.*
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.junga.cupofsoju.model.UserData
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity(),View.OnClickListener {

    val TAG = "MainActivity"

    lateinit var navDrawer:Drawer;
    var user : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buildDrawer()

        drawer.setOnClickListener(this)

        qrcode.setOnClickListener(this)
    }



    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.drawer -> navDrawer.openDrawer()
            R.id.qrcode -> getQRcode()
        }
    }



    private fun buildDrawer(){

        val item1 = PrimaryDrawerItem().withIdentifier(1).withName("요금제 변경")
        val item2 = PrimaryDrawerItem().withIdentifier(2).withName("가맹 리스트")
        val item3 = PrimaryDrawerItem().withIdentifier(3).withName("회원정보 수정")
        val item4 = SecondaryDrawerItem().withIdentifier(4).withName("로그아웃")



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
                item2,
                item3,
                DividerDrawerItem(),
                item4
            )
            .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean {
                    // do something with the clicked item :D
                    when(position){
                        0->startActivity<BillingActivity>()
                        1->startActivity<BillingActivity>()
                        2->startActivity<BillingActivity>()
                    }
                    return false
                }
            })
            .withSelectedItem(-1) //This is for making none of items are not selected when it is initialized.
            .build()

    }

    private fun getQRcode(){
        Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show()

        val integrator = IntentIntegrator(this)
        integrator.setBeepEnabled(false)
        integrator.captureActivity = CustomScannerActivity::class.java
        integrator.initiateScan()

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == Activity.RESULT_OK){
            val scanResult : IntentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
            val re = scanResult.contents;
//            Toast.makeText(this,"This is result!!!!!!!"+re,Toast.LENGTH_LONG).show()
            validCheck()
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    private fun validCheck(){

        // TODO :Check if that store number is valid.


        // today, month left

        db.collection("User")
            .whereEqualTo("email", user.email) // <-- This line
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("useremail!!!!",user.email)
                    var querySnapshot = task.result
                    var doc = querySnapshot!!.documents.get(0)

                    //Get Document ID for updating billing type
                    var docID= doc.id

                    //Need to be deleted
                    var oldUser = doc.toObject(UserData::class.java)
                    var todayLeft = oldUser!!.todayLeft
                    var monthLeft = oldUser!!.monthLeft
                    Log.d(TAG,"user ${oldUser!!.email}의 old left${oldUser!!.todayLeft}의 아이디디디 $docID")


                    //if todayLeft and monthLeft is bigger than 0, minus -1 and complete to use it.
                    if(todayLeft>0 && monthLeft>0){updateUserLeft(docID,todayLeft,monthLeft)}else{
                        Snackbar.make(layout,"사용하실 수 있는 사용권이 없습니다.",Snackbar.LENGTH_SHORT).show()
                    }




                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }


    }


    private fun updateUserLeft(docId : String,todayLeft :Int, monthLeft :Int){
        db.collection("User").document(docId)
            .update(
                mapOf(
                    "todayLeft" to todayLeft-1,
                    "monthLeft" to monthLeft-1
                )
            ).addOnSuccessListener { Log.d(TAG,"succeed") }
            .addOnFailureListener { Log.d(TAG,"Failed") }
    }


}
