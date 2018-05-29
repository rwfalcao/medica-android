package com.example.android.models;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

import com.example.android.pi2.IngestionActivity;
import com.example.android.pi2.NotificationApplication;
import com.example.android.pi2.R;

import static com.example.android.pi2.NotificationApplication.CHANNEL_1_ID;

public class Alarm extends BroadcastReceiver{

    String username;
    String medname, userid, schedId, ingestTime, ingestDate;
    NotificationManagerCompat notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        notificationManager = NotificationManagerCompat.from(context);

        username = intent.getStringExtra("username");
        medname = intent.getStringExtra("medname");
        userid = intent.getStringExtra("userid");
        schedId = intent.getStringExtra("schedId");
        ingestTime = intent.getStringExtra("ingestTime");
        ingestDate = intent.getStringExtra("ingestDate");


        sendIngestNotification(context, username, medname, userid, schedId,ingestTime, ingestDate);
        MediaPlayer mediaplayer = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI);
        mediaplayer.start();
    }

    public void sendIngestNotification(Context context, String username, String medname, String userid, String schedId, String ingestTime, String ingestDate){

        Intent ingestIntent = new Intent(context, IngestionActivity.class);

        ingestIntent.putExtra("username", username);
        ingestIntent.putExtra("medname", medname);
        ingestIntent.putExtra("userid", userid);
        ingestIntent.putExtra("schedId", schedId);
        ingestIntent.putExtra("ingestTime", ingestTime);
        ingestIntent.putExtra("ingestDate", ingestDate);

        PendingIntent IngestPIntent = PendingIntent.getActivity(context,
                0, ingestIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.meds_icon)
                .setContentTitle(username)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.clock_icon))
                .setContentText(medname)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setColor(Color.RED)
                .setOngoing(true)
                .setContentIntent(IngestPIntent)
                .setAutoCancel(true)

                .build();

        notificationManager.notify(1, notification);
    }
}
