package br.com.exemplenotifications

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessageService : FirebaseMessagingService() {

    val tag = "FirebaseMessageService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.i(tag,remoteMessage.from.toString())

        if (remoteMessage.notification != null){
            this.ShowNotification("1234",
                remoteMessage.notification?.title.toString(),
                remoteMessage.notification?.body.toString())
        }
    }
}