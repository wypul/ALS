package com.pavan.slidingmenu;

/**
 * Created by vidursachdeva on 06/04/15.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // For our recurring task, we'll just display a message

        GPSTracker gps = new GPSTracker(arg0);
        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            // \n is for new line



            //Toast.makeText(arg0, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();


        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

    }

}