package com.example.arcana.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.arcana.R
import com.example.arcana.model.ProfileItem
import com.example.arcana.viewmodel.FirestoreViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestoreViewModel: FirestoreViewModel
    var database = FirebaseDatabase.getInstance()
    var myRef = database.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        signInAnoymously()
        firestoreViewModel = ViewModelProviders.of(this).get(FirestoreViewModel::class.java)
        bt_register.setOnClickListener {
            registerUser()
        }
        realtimeDatabase()
    }

    private fun registerUser(){
        Log.d("Excal", "Excal clicked")
        val profileItem = ProfileItem(
            et_name.text.toString(),
            et_email.text.toString(),
            et_address.text.toString(),
            et_hobby.text.toString(),
            et_job.text.toString()
        )
        firestoreViewModel.saveProfileToFirestore(profileItem)
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("email", et_email.text.toString())
        startActivity(intent)
    }

    private fun signInAnoymously(){
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("auth", "signInAnonymously:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("auth", "signInAnonymously:failure", task.exception)
                }
            }
    }

    private fun realtimeDatabase(){

        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var Humidity  = dataSnapshot.child("node 1").child("humidity").value
                et_name.setText(Humidity.toString())
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        myRef.addValueEventListener(menuListener)
    }

}