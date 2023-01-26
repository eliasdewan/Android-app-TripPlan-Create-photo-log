package com.example.tripplan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;

public class myDbAdapter {

    myDbHelper myhelper;

    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
        // Log.w("this","created adapter in constructor");
    }


    static class myDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_NAME = "Trips";

        private static final String UID = "trip_id";
        private static final String Title = "title";

        private static final String FromPage = "fromPage";
        private static final String ToPage = "toPage";

        private static final String DateTime = "timestamp";
        private static final String ReaderComment = "readerComment";
        private static final String SupportComment = "supportComment";


        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "("
                + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Title + " VARCHAR(255),"

                + FromPage + " INTEGER,"
                + ToPage + " INTEGER,"

                + DateTime + " VARCHAR(255),"
                + SupportComment + " VARCHAR(255),"
                + ReaderComment + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        //Initialize - constructor
        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.w("this", "done the super, created the initialize and new database");
            this.context = context;
            //context.deleteDatabase(DATABASE_NAME); /* For resetting Database */
        }


        public void onCreate(SQLiteDatabase db) {
            Log.w("this", "In the on create");
            try {
                Log.w("this", "TABLE CREATED+++");
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                //Message.message(context,""+e);
                Log.w("this", "TABLE NOT CREATED");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("this", "iN THE ONUPGRADE");
            try {
                //Message.message(context, "onUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                //Message.message(context, ""+e);
            }
        }

    }
}
