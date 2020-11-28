package com.example.arcana.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.arcana.R
import com.example.arcana.viewmodel.FirestoreViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var firestoreViewModel: FirestoreViewModel
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        firestoreViewModel = ViewModelProviders.of(this).get(FirestoreViewModel::class.java)
        val bundle = intent.extras
        email = bundle?.getString("email")!!
        loadProfile()
    }

    private fun loadProfile(){
        firestoreViewModel.getProfileFromFirestore(email).observe(this, Observer { profileData ->
            tv_email.text = profileData.email
            tv_name.text = profileData.name
            tv_address.text = profileData.address
            tv_hobby.text = profileData.hobby
            tv_job.text = profileData.job
        })
    }
}