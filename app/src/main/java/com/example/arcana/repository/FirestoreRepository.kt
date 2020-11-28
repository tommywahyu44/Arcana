package com.example.arcana.repository

import android.util.Log
import com.example.arcana.model.ProfileItem
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreRepository {
    val TAG = "FIRESTORE"
    val db = Firebase.firestore


    // save address to firebase
    fun saveProfile(profileItem: ProfileItem): DocumentReference {
        return db.collection("users").document(profileItem.email)
    }

    // get saved addresses from firebase
    fun getProfile(email: String): DocumentReference {
        return db.collection("users").document(email)
    }

}