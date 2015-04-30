package com.pavan.slidingmenu;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by vidursachdeva on 23/04/15.
 */

public class MyService1 extends Service {
    public PendingIntent pendingIntent;
    public AlarmManager manager;

    public MyService1() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks yo
        // u can launch a new thread here...
        Intent alarmIntent = new Intent(this, AlarmReceiver1.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        startAlarm();
    }

    public void startAlarm() {
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "All Set", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

    }
}