package com.example.usersfirebase2

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    var editTextUserName: EditText? = null
    var editTextUserNumber: EditText? = null
    var editTextUserAddress: EditText? = null

    var db = FirebaseFirestore.getInstance()
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUserName = findViewById(R.id.userName);
        editTextUserNumber = findViewById(R.id.userNum);
        editTextUserAddress = findViewById(R.id.userAddress);

    }

    fun saveToFirebase(v: View?) {

        showDialog()
        val userName = editTextUserName!!.text.toString()
        val userNumber = editTextUserNumber!!.text.toString()
        val userAddress = editTextUserAddress!!.text.toString()

        // Create a new user with a first and last name
        val Users: MutableMap<String, Any> = HashMap()
        Users["user_name"] = userName
        Users["user_number"] = userNumber
        Users["user_address"] = userAddress

// Add a new document with a generated ID
        db.collection("Users")
                .add(Users)
                .addOnSuccessListener { documentReference ->
                    Log.d("Ola",
                            "DocumentSnapshot added with ID: " + documentReference.id
                    )
                    hideDialog()
                }
                .addOnFailureListener { e ->
                    Log.w("Ola", "Error adding document", e)
                    hideDialog()
                }

        editTextUserName!!.text.clear()
        editTextUserNumber!!.text.clear()
        editTextUserAddress!!.text.clear()
    }


    private fun showDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Add user to Firebase")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    private fun hideDialog() {
        if (progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    fun showAllUsers(view: View) {

        var i = Intent(this,MainActivity2::class.java)
        startActivity(i)
    }
}