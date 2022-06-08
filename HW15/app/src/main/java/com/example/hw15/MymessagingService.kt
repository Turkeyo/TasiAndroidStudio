package com.example.hw15

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MymessagingService : FirebaseMessagingService(){
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("Firebase","onNewToken $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message?.let {
            Log.e("Firebase", it.from.toString())
            for (entry in it.data.entries)
                Log.e("message", "${entry.key}/${entry.value}")
        }
    }
}