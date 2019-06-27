package com.junga.cupofsoju

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.junga.cupofsoju.model.ProjectValue
import kotlinx.android.synthetic.main.activity_billing.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseUser
import com.junga.cupofsoju.model.UserData
import org.jetbrains.anko.startActivity


class BillingActivity : AppCompatActivity(),View.OnClickListener{

    //Firebase
    val TAG = "BillingActivity"
    lateinit var db : FirebaseFirestore;
    lateinit var auth :FirebaseAuth;
    lateinit var user :FirebaseUser;


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

    override fun onClick(p0: View?) {

        when(p0!!.id){
            R.id.bill1 ->{registerBilling(ProjectValue.Companion.SINGLE_BILL_SMALL)}
            R.id.bill2 ->{registerBilling(ProjectValue.Companion.SINGLE_BILL_MEDIUM)}
            R.id.bill3 ->{registerBilling(ProjectValue.Companion.SINGLE_BILL_LARGE)}
        }
    }

    private fun registerBilling(billing_type : Int){
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
                    Log.d(TAG,"user ${oldUser!!.email}의 old left${oldUser!!.todayLeft}의 아이디디디 $docID")

                    updateBilling(billing_type,docID)

                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
    }


    private fun updateBilling(billing_type: Int,doc_ID:String){
        var todayLeft:Int =0
        var monthLeft:Int = 0

        when(billing_type){
            ProjectValue.Companion.SINGLE_BILL_SMALL->{
                todayLeft = 1
                monthLeft =20
            }
            ProjectValue.Companion.SINGLE_BILL_MEDIUM ->{
                todayLeft = 2
                monthLeft =30
            }
            ProjectValue.Companion.SINGLE_BILL_LARGE ->{
                todayLeft = 3
                monthLeft =50
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
                Log.d(TAG,"All updated")
            }.addOnFailureListener {
                Log.d(TAG,"Failed")
            }




    }
}
