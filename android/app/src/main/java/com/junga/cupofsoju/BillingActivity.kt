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


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.junga.cupofsoju.model.ProjectValue
import com.junga.cupofsoju.model.UserData
import kotlinx.android.synthetic.main.activity_billing.*


class BillingActivity : AppCompatActivity(), View.OnClickListener {

    //Firebase
    val TAG = "BillingActivity"
    lateinit var db: FirebaseFirestore;
    lateinit var auth: FirebaseAuth;
    lateinit var user: FirebaseUser;
    var type: Int = 0;
    var groupPriceToggle = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)

        bill1.setOnClickListener(this)
        bill2.setOnClickListener(this)
        bill3.setOnClickListener(this)
        group_price.setOnClickListener(this)

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!

    }

    override fun onStart() {
        super.onStart()
        setText()
    }

    override fun onClick(p0: View?) {

        when (p0!!.id) {
            R.id.bill1 -> {

                val dialog = AlertDialog.Builder(this)
                dialog.setMessage("요금제를 신청 하시겠습니까??")
                    .setCancelable(false)
                    .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->

                        if (type == ProjectValue.Companion.SINGLE) registerBilling(ProjectValue.Companion.SINGLE_BILL_SMALL)
                        else registerBilling(ProjectValue.Companion.GROUP_BILL_SMALL)
                        dialogInterface.cancel()
                    })
                    .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.cancel()
                    })
                val alert = dialog.create()
                alert.show()
            }
            R.id.bill2 -> {

                val dialog = AlertDialog.Builder(this)
                dialog.setMessage("요금제를 신청 하시겠습니까??")
                    .setCancelable(false)
                    .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->

                        if (type == ProjectValue.Companion.SINGLE) registerBilling(ProjectValue.Companion.SINGLE_BILL_MEDIUM)
                        else registerBilling(ProjectValue.Companion.GROUP_BILL_MEDIUM)
                        dialogInterface.cancel()
                    })
                    .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.cancel()
                    })
                val alert = dialog.create()
                alert.show()
            }
            R.id.bill3 -> {

                val dialog = AlertDialog.Builder(this)
                dialog.setMessage("요금제를 신청 하시겠습니까??")
                    .setCancelable(false)
                    .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->

                        if (type == ProjectValue.Companion.SINGLE) registerBilling(ProjectValue.Companion.SINGLE_BILL_LARGE)
                        else registerBilling(ProjectValue.Companion.GROUP_BILL_LARGE)
                        dialogInterface.cancel()
                    })
                    .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.cancel()
                    })
                val alert = dialog.create()
                alert.show()

            }
            R.id.group_price ->{
                if(!groupPriceToggle){
                price1.setText("52000원")
                price2.setText("69000원")
                price3.setText("78000원")


                    c1.setText(R.string.s1)
                    c2.setText(R.string.s2)
                    c3.setText(R.string.s3)
                recommendation.setText(R.string.single_reco)
                    group_price.setText("단체")
                    groupPriceToggle = true
                }else{
                    price1.setText("19000원")
                    price2.setText("22000원")
                    price3.setText("29000원")
                    recommendation.setText(R.string.group_reco)


                    c1.setText(R.string.g1)
                    c2.setText(R.string.g2)
                    c3.setText(R.string.g3)
                    group_price.setText("개인")
                    groupPriceToggle = false
                }

            }
        }
    }

    private fun registerBilling(billing_type: Int) {
        db.collection("User")
            .whereEqualTo("email", user.email) // <-- This line
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("useremail!!!!", user.email)
                    val querySnapshot = task.result
                    val doc = querySnapshot!!.documents.get(0)

                    //Get Document ID for updating billing type
                    val docID = doc.id

                    //Need to be deleted
                    val oldUser = doc.toObject(UserData::class.java)
                    Log.d(TAG, "user ${oldUser!!.email}의 old left${oldUser!!.todayLeft}의 아이디디디 $docID")

                    updateBilling(billing_type, docID)

                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }

    }

    private fun updateBilling(billing_type: Int, doc_ID: String) {


        var todayLeft: Int = 0
        var monthLeft: Int = 0

        when (billing_type) {
            ProjectValue.Companion.SINGLE_BILL_SMALL -> {
                todayLeft = 1
                monthLeft = 10
            }
            ProjectValue.Companion.SINGLE_BILL_MEDIUM -> {
                todayLeft = 2
                monthLeft = 40
            }
            ProjectValue.Companion.SINGLE_BILL_LARGE -> {
                todayLeft = 3
                monthLeft = 50
            }
            ProjectValue.Companion.GROUP_BILL_SMALL -> {
                todayLeft = 10
                monthLeft = 60
            }
            ProjectValue.Companion.GROUP_BILL_MEDIUM -> {
                todayLeft = 14
                monthLeft = 80
            }
            ProjectValue.Companion.GROUP_BILL_LARGE -> {
                todayLeft = 20
                monthLeft = 140
            }
        }

        db.collection("User").document(doc_ID)
            .update(
                mapOf(
                    "bill" to billing_type,
                    "todayLeft" to todayLeft,
                    "monthLeft" to monthLeft
                )

            ).addOnSuccessListener {
                Log.d(TAG, "All updated")
                Toast.makeText(this,"요금제가 업데이트 되었습니다.",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Log.d(TAG, "Failed")
            }

    }

    private fun setText() {
        db.collection("User")
            .whereEqualTo("email", user.email) // <-- This line
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("useremail!!!!", user.email)

                    val querySnapshot = task.result
                    if(querySnapshot!!.documents.size!=0){
                    val doc = querySnapshot!!.documents.get(0)
                    val oldUser = doc.toObject(UserData::class.java)
                    type = oldUser!!.type
                    when (type) {
                        0 -> {
                            Log.d(TAG,"type0")
                        }
                        1 -> {
                            Log.d(TAG,"type1")
                        }
                    }
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
    }


    //Billing Recommend Algorithm
    private fun recommendBilling(howMuch : Int,howMany: Int) : Int{
        // Explanation
        // This app aims to make people drink more frequently with reasonable price, not to make them drink a lot.
        // So We decided to put weight on the parameter "How many time do you drink in a week?"
        var point1 = howMuch*3
        var point2 = when(howMany){
            0-> 10 // 1-2회
            1-> 15 // 3-4회
            2-> 22 // 5회 이상
            else -> 0
        }
        var sum = point1+point2
            if(0<sum && sum<20){
                return ProjectValue.Companion.SINGLE_BILL_SMALL
            }else if (sum>=20 && sum<30){
                return ProjectValue.Companion.SINGLE_BILL_MEDIUM
            }else{
                return ProjectValue.Companion.SINGLE_BILL_LARGE
            }
    }
}
