package com.example.silencer;
//import android.media.AudioManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class RequestReceiver2 extends BroadcastReceiver {
    private AudioManager lAudioManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder2=new NotificationCompat.Builder(context,"Delta2")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Your phone is on NORMAL MODE!")
                .setContentText("Silencer")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManagerCompat2=NotificationManagerCompat.from(context);
        notificationManagerCompat2.notify(123,builder2.build());



        lAudioManager =(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        lAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);


    }
}
