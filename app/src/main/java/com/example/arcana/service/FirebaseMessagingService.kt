package com.example.arcana.service

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.arcana.view.NotificationActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")

        NotificationActivity.startIntent(this, remoteMessage.notification?.title ?: "", remoteMessage.notification?.body ?: "")
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }



    companion object {

        private const val TAG = "FIREBASE_MESSAGING"
    }
}