package com.junga.cupofsoju

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_test.*
import java.io.ByteArrayOutputStream

class test : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

//        var dummyRef = storageRef.child("images/dummy.png")

//        dummyRef.downloadUrl.addOnSuccessListener {
////            Log.d("AHHHHhHHHHHHHHH",it.toString())
////            Glide.with(this).load(it).into(testview)
////        }.addOnFailureListener {
////            Log.d("WHAT the fuck is wrong",it.message)
////        }

        // Get the data from an ImageView as bytes
       handler.sendEmptyMessage(0)

    }

    val handler :Handler = object: Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg!!.what){
                0-> {

                    var storage = FirebaseStorage.getInstance("gs://cupofsoju-a47b2.appspot.com")
                    var storageRef = storage.reference

                    val bitmap = (testview.drawable!! as BitmapDrawable).bitmap
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val data = baos.toByteArray()

                    var uploadTask = storageRef.child("images/upload.png").putBytes(data)
                    uploadTask.addOnFailureListener {
                        // Handle unsuccessful uploads
                        Log.d("not UPLOADED!!!!!!", "fuck")
                    }.addOnSuccessListener {
                        // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                        // ...
                        Log.d("UPLOADED!!!!!!","hehe" )
                    }

                }
            }
        }
    }
}
