package com.pavan.slidingmenu;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vipul on 2/4/15.
 */
public class Message {
    public static void message(Context context, String mesage){
        Toast.makeText(context, mesage,Toast.LENGTH_LONG).show();
    }
}
