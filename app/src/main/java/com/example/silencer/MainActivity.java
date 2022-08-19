package com.example.silencer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3;
    AlarmManager alarmManager1,alarmManager2;
    TextView text1,text2;
    Calendar calendar,calendar2;
    PendingIntent pendingIntent,pendingIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && !nm.isNotificationPolicyAccessGranted())
            startActivity(new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS));
        createNotificationChannel1();
        createNotificationChannel2();
        b1 =findViewById(R.id.silenttime);
        text1 =findViewById(R.id.selectedTime);
        text2=findViewById(R.id.endtime);
        b2=findViewById(R.id.submit);
        b3=findViewById(R.id.normaltime);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showtimepicker1();
            }
    });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
                setNormal();
                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtimepicker2();
            }
        });
}

    private void setAlarm() {
        alarmManager1 =(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent1=new Intent(this, RequestReceiver1.class);
        pendingIntent=PendingIntent.getBroadcast(this,0,intent1,PendingIntent.FLAG_IMMUTABLE);
        alarmManager1.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

    }
    private void setNormal()
    {
        alarmManager2 =(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent2=new Intent(this, RequestReceiver2.class);
        pendingIntent2=PendingIntent.getBroadcast(this,0,intent2,PendingIntent.FLAG_IMMUTABLE);
        alarmManager2.setExact(AlarmManager.RTC_WAKEUP,calendar2.getTimeInMillis(),pendingIntent2);

    }

    private void showtimepicker1() {
        MaterialTimePicker picker=new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm time")
                .build();
        picker.show(getSupportFragmentManager(),"Time");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(picker.getHour()>12){
                    text1.setText(String.format("%02d",(picker.getHour()-12))+" : "+String.format("%02d",(picker.getMinute()))+" PM");
                }
                else
                    text1.setText(String.format("%02d",(picker.getHour()))+" : "+String.format("%02d",(picker.getMinute()))+" AM");
                calendar=Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE,picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
            }
        });
    }
    private void showtimepicker2() {
        MaterialTimePicker picker=new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm time")
                .build();
        picker.show(getSupportFragmentManager(),"Time");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(picker.getHour()>12){
                    text2.setText(String.format("%02d",(picker.getHour()-12))+" : "+String.format("%02d",(picker.getMinute()))+" PM");
                }
                else
                    text2.setText(String.format("%02d",(picker.getHour()))+" : "+String.format("%02d",(picker.getMinute()))+" AM");
                calendar2=Calendar.getInstance();
                calendar2.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar2.set(Calendar.MINUTE,picker.getMinute());
                calendar2.set(Calendar.SECOND,0);
                calendar2.set(Calendar.MILLISECOND,0);
            }
        });
    }
    private void createNotificationChannel1()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Channel1";
            String description="Channel for Receiver 1";
            int importance=NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel1=new NotificationChannel("Delta1",name,importance);
            channel1.setDescription(description);
            NotificationManager notificationManager1=getSystemService(NotificationManager.class);
            notificationManager1.createNotificationChannel(channel1);
        }
    }
    private void createNotificationChannel2()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Channel2";
            String description="Channel for Receiver 2";
            int importance=NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel2=new NotificationChannel("Delta1",name,importance);
            channel2.setDescription(description);
            NotificationManager notificationManager2=getSystemService(NotificationManager.class);
            notificationManager2.createNotificationChannel(channel2);
        }
    }
}

