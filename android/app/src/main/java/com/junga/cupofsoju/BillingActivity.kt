package com.junga.cupofsoju

import android.os.Bundle
import android.util.Log
import android.view.View
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)

        bill1.setOnClickListener(this)
        bill2.setOnClickListener(this)
        bill3.setOnClickListener(this)

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
                if (type == ProjectValue.Companion.SINGLE) registerBilling(ProjectValue.Companion.SINGLE_BILL_SMALL)
                else registerBilling(ProjectValue.Companion.GROUP_BILL_SMALL)
            }
            R.id.bill2 -> {
                if (type == ProjectValue.Companion.SINGLE) registerBilling(ProjectValue.Companion.SINGLE_BILL_MEDIUM)
                else registerBilling(ProjectValue.Companion.GROUP_BILL_MEDIUM)
            }
            R.id.bill3 -> {
                if (type == ProjectValue.Companion.SINGLE) registerBilling(ProjectValue.Companion.SINGLE_BILL_LARGE)
                else registerBilling(ProjectValue.Companion.GROUP_BILL_LARGE)
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
                monthLeft = 20
            }
            ProjectValue.Companion.SINGLE_BILL_MEDIUM -> {
                todayLeft = 2
                monthLeft = 30
            }
            ProjectValue.Companion.SINGLE_BILL_LARGE -> {
                todayLeft = 3
                monthLeft = 50
            }
            ProjectValue.Companion.GROUP_BILL_SMALL -> {
                todayLeft = 10
                monthLeft = 200
            }
            ProjectValue.Companion.GROUP_BILL_MEDIUM -> {
                todayLeft = 20
                monthLeft = 300
            }
            ProjectValue.Companion.GROUP_BILL_LARGE -> {
                todayLeft = 30
                monthLeft = 500
            }
        }

        db.collection("User").document(doc_ID)
            .update(
                mapOf(
                    "type" to billing_type,
                    "todayLeft" to todayLeft,
                    "monthLeft" to monthLeft
                )

            ).addOnSuccessListener {
                Log.d(TAG, "All updated")
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
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
    }


    //Billing Recommend Algorithm
    private fun recommendBilling(howMuch : Int,howMany: Int){
        // Explanation
        // This app aims to make people drink more frequently with reasonable price, not to make them drink a lot.
        // So We decided to put weight on the parameter "How many time do you drink in a week?"


    }
}
