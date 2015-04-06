package com.pavan.slidingmenu.slidelist;

import android.app.Fragment;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.pavan.slidingmenu.R;

/**
 * Created by vidursachdeva on 16/02/15.
 */
public class NT_Fragment extends Fragment {

    NotificationManager NM;
    EditText one, two, three;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        one = (EditText) getActivity().findViewById(R.id.editText1);
        two = (EditText) getActivity().findViewById(R.id.editText2);
        three = (EditText) getActivity().findViewById(R.id.editText3);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("deprecation")
    public void notify(View vobj) {
        String title = one.getText().toString();
        String subject = two.getText().toString();
        String body = three.getText().toString();
        NM = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification(android.R.drawable.
                stat_notify_more, title, System.currentTimeMillis());
        PendingIntent pending = PendingIntent.getActivity(
                getActivity().getApplicationContext(), 0, new Intent(), 0);
        notify.setLatestEventInfo(getActivity().getApplicationContext(), subject, body, pending);
        NM.notify(0, notify);
    }
}
