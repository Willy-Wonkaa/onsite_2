package com.example.silencer;
//import android.media.AudioManager;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class RequestReceiver1 extends BroadcastReceiver {
    private AudioManager mAudioManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder1=new NotificationCompat.Builder(context,"Delta1")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Your phone is on SILENT MODE!")
                .setContentText("Silencer")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManagerCompat1=NotificationManagerCompat.from(context);
        notificationManagerCompat1.notify(123,builder1.build());


        mAudioManager =(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

    }
}
