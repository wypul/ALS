package com.pavan.slidingmenu;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by vipul on 9/4/15.
 */
public class VipulDatabaseAdapterLocation extends Activity{
    VipulHelper helper;

    public VipulDatabaseAdapterLocation(Context context){
        helper = new VipulHelper(context);
    }


    static class VipulHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "vipuldatabase";
        private static final String TABLE_NAME = "locationtable";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String ROOM_NO = "room_no";
        private static final String Latitude = "latitude";
        private static final String Longitude = "longitude";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ROOM_NO+" VARCHAR(255), "+Latitude+" VARCHAR(255), "+Longitude+" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME ;
        private Context context;

        public VipulHelper(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Message.message(context,"Cunstructor called");
        }



        @Override
        public void onCreate(SQLiteDatabase db) {

            try{
                db.execSQL(CREATE_TABLE);
                ContentValues contentValues = new ContentValues();
                contentValues.put(VipulHelper.ROOM_NO,"F-102");
                contentValues.put(VipulHelper.Latitude,"36.76768");
                contentValues.put(VipulHelper.Longitude,"100.98769");
                db.insert(VipulHelper.TABLE_NAME,null,contentValues);
                Message.message(context,"oncreate called");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i2) {
            try {
                db.execSQL(DROP_TABLE);
                Message.message(context,"onupgrade called");
                onCreate(db);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
