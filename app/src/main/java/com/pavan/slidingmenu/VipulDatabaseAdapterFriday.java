package com.pavan.slidingmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by vipul on 2/4/15.
 */
public class VipulDatabaseAdapterFriday {
    VipulHelper helper;

    public VipulDatabaseAdapterFriday(Context context){
        helper = new VipulHelper(context);
    }

    public long insertData(String room_no, String subject){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VipulHelper.ROOM_NO,room_no);
        contentValues.put(VipulHelper.SUBJECT,subject);
        long id = db.insert(VipulHelper.TABLE_NAME,null,contentValues);
        return id;
    }

    public void checkdb(){
        SQLiteDatabase db = helper.getWritableDatabase();
        helper.onUpgrade(db, 1, 2);
    }

    public void initialize(){
        SQLiteDatabase db = helper.getWritableDatabase();
        helper.onCreate(db);
    }

    public ArrayList getData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {VipulHelper.UID,VipulHelper.ROOM_NO,VipulHelper.SUBJECT};
        Cursor cursor = db.query(VipulHelper.TABLE_NAME,columns,null,null,null,null,null);
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()){
            int cid = cursor.getInt(0);
            String room_no = cursor.getString(1);
            String subject = cursor.getString(2);
            arrayList.add(cursor.getString(1));
            arrayList.add(cursor.getString(2));
        }
        return arrayList;
    }

    static class VipulHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "vipuldatabase";
        private static final String TABLE_NAME = "fridaytable";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String ROOM_NO = "room_no";
        private static final String SUBJECT = "subject";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ROOM_NO+" VARCHAR(255), "+SUBJECT+" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME ;
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
                Message.message(context,"oncreate called");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i2) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
