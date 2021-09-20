package com.example.app

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import androidx.core.app.NotificationCompat
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.badge.BadgeDrawable
import java.util.*
import com.google.android.material.badge.BadgeUtils


class NotificationFirebase :FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val notification = remoteMessage.notification
        sendNotification(notification?.title,notification?.body)
    }
    fun sendNotification(title: String?, message: String?) {

        var builder = NotificationCompat.Builder(this, ChannelID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                ChannelID,
                Channel_name,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                this.description = DECSRIPTION;
            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel);
        }
        var notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(getNotificationID(), builder.build())

    }
    fun getNotificationID(): Int{
        return Date().time.toUInt().toInt()
    }
    companion object{
        val ChannelID ="push_notifr"
        val Channel_name = "channel 1"
        val DECSRIPTION = "this chane 1"

    }
}

