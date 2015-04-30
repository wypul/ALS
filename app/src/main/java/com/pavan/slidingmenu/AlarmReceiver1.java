package com.pavan.slidingmenu;

/**
 * Created by vidursachdeva on 23/04/15.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class AlarmReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {

        NotificationManager NM;
        String title = "Hi";
        String subject = "Your";
        String body = "Maaa";
        NM = (NotificationManager) arg0.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification(android.R.drawable.
                stat_notify_more, title, System.currentTimeMillis());
        PendingIntent pending = PendingIntent.getActivity(
                arg0.getApplicationContext(), 0, new Intent(), 0);
        notify.setLatestEventInfo(arg0.getApplicationContext(), subject, body, pending);
        NM.notify(0, notify);
        Toast.makeText(arg0, "Your lllllll ", Toast.LENGTH_LONG).show();

    }
}
