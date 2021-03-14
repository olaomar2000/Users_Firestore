package com.example.usersfirebase2

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ourproject.Adapter.UsersAdapter
import com.example.ourproject.modle.Users
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity2 : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()

    var recView: RecyclerView? = null
    private var progressDialog: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        recView = findViewById(R.id.recView)
        showDialog()
        getAllUsers()
    }


    private fun showDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("view users ...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    private fun hideDialog() {
        if (progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    private fun getAllUsers() {
        val contactList= mutableListOf<Users>()
        db.collection("Users")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot){
                        Log.e("Ola","${document.id} => ${document.data}")
                        val id = document.id
                        val data = document.data
                        val UserName = data["user_name"] as String?
                        val UserNumber = data["user_number"] as String?
                        val UserAddress = data["user_address"] as String?
                        contactList.add(Users(id, UserName, UserNumber,UserAddress))
                    }
                    recView!!.layoutManager =
                            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                    recView!!.setHasFixedSize(true)
                    val contactsAdapter = UsersAdapter(this, contactList)
                    recView!!.adapter = contactsAdapter
                   hideDialog()
                }
                .addOnFailureListener { exception ->
                    exception.message?.let { Log.e("Ola", it) }

                }
    }
}