package com.example.medkit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ReminderDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME= "Reminder.db";
    private static final String TABLE_NAME = "med_reminder";
    private static final int VERSION_NUMBER = 1;
    private static final String ID="_id";
    private static final String REMINDER="Reminder";
    private static final String TIME="Time";
    private static final String DATE="Date";
    private static final String CREATE_TABLE=
            "CREATE TABLE "+TABLE_NAME+" ( "
            + ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + REMINDER +" VARCHAR(255), "
            + TIME +" TEXT, "
            + DATE +" TEXT  ); " ;
    private static final String DROP_TABLE=" DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_ALL=" SELECT * FROM "+TABLE_NAME;
    private static final String SELECT_ID=" SELECT"+ ID +"FROM "+TABLE_NAME;

    private  Context context;


    public ReminderDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
            //Toast.makeText(context, " Reminder is Saved On Create",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            //Toast.makeText(context, " Reminder Not Saved",Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {

            db.execSQL(DROP_TABLE);
            onCreate(db);
            //Toast.makeText(context,"Reminder is Saved On Upgrade", Toast.LENGTH_SHORT).show();

        }catch (Exception e){

            //Toast.makeText(context,"Reminder Not Saved", Toast.LENGTH_SHORT).show();
        }


    }

    public void insertData(String reminder, String time, String date){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(REMINDER,reminder);
        contentValues.put(TIME,time);
        contentValues.put(DATE,date);
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

    }

    public Cursor displayData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(SELECT_ALL,null);
        return cursor;
    }

    public Cursor getId(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(SELECT_ID,null);
        return cursor;
    }

    public void deleteData(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,ID+" = ?", new String[]{id});

    }
}
