package com.example.dell.smartfixapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class firebasePushnotification extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getNotification().getBody());

    }

    public void showNotification(String message)
    {
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,GetStarted.class),0);
        Notification notification = new NotificationCompat.Builder(this).setContentTitle("FCM").setContentText(message).setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).setAutoCancel(true).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);


    }





}
