package com.pavan.slidingmenu;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by vipul on 23/4/15.
 */
public class VipulDatabaseAdapterSubject extends Activity{

    VipulHelper helper;

    public VipulDatabaseAdapterSubject(Context context){
        helper = new VipulHelper(context);
    }

    public void initialize(){
        SQLiteDatabase db = helper.getWritableDatabase();
        helper.onCreate(db);
    }
    public void checkdb(){
        SQLiteDatabase db = helper.getWritableDatabase();
        helper.onUpgrade(db, 1, 2);
    }

    public long insertData(String subject, String present, String absent){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VipulHelper.SUBJECT,subject);
        contentValues.put(VipulHelper.PRESENT,present);
        contentValues.put(VipulHelper.ABSENT,absent);
        long id = db.insert(VipulHelper.TABLE_NAME,null,contentValues);
        return id;
    }

    public ArrayList getData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {VipulHelper.UID,VipulHelper.SUBJECT,VipulHelper.PRESENT,VipulHelper.ABSENT};
        Cursor cursor = db.query(VipulHelper.TABLE_NAME,columns,null,null,null,null,null);
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()){
            arrayList.add(cursor.getString(1));
            arrayList.add(cursor.getString(2));
            arrayList.add(cursor.getString(3));
        }
        return arrayList;
    }


    static class VipulHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "vipuldatabasesubject";
        private static final String TABLE_NAME = "attendance";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String PRESENT = "present";
        private static final String ABSENT = "absent";
        private static final String SUBJECT = "subject";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+SUBJECT+" VARCHAR(255), "+PRESENT+" VARCHAR(255), "+ABSENT+" VARCHAR(255));";
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