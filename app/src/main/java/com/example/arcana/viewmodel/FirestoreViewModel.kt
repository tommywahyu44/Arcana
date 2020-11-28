package com.example.arcana.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arcana.model.ProfileItem
import com.example.arcana.repository.FirestoreRepository

class FirestoreViewModel : ViewModel() {

    var firebaseRepository = FirestoreRepository()
    var profileData: MutableLiveData<ProfileItem> = MutableLiveData()
    val TAG = "FIRESTORE"

    fun saveProfileToFirestore(profileItem: ProfileItem) {
        firebaseRepository.saveProfile(profileItem).set(profileItem)
    }

    fun getProfileFromFirestore(email: String): LiveData<ProfileItem> {
        firebaseRepository.getProfile(email).get().addOnSuccessListener { document ->
            profileData.value = document?.toObject(ProfileItem::class.java)!!
        }.addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents.", exception)
        }
        return profileData
    }

}