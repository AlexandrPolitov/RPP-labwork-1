package com.e.lab_work_31;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lab3DB";
    public static final String TABLE_STUDENTS = "students";

    public static final String KEY_ID = "id";
    public static final String KEY_F = "f";
    public static final String KEY_I = "i";
    public static final String KEY_O = "o";
    public static final String KEY_FIO = "fio";
    public static final String KEY_TIME = "time";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        Log.e ("my", "onCreate");
        if (DATABASE_VERSION == 1) {
            database.execSQL("create table if not exists " + TABLE_STUDENTS + " ("
                    + KEY_ID + " integer not null primary key autoincrement,"
                    + KEY_FIO + " text,"
                    + KEY_TIME + " text" + ");");
        }
        else {
            database.execSQL("create table if not exists "  + TABLE_STUDENTS +" ("
                    + KEY_ID + " integer not null primary key autoincrement,"
                    + KEY_F + " text,"
                    + KEY_I + " text,"
                    + KEY_O + " text,"
                    + KEY_TIME + " text" + ");");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion > oldVersion){
            Log.e ("my", "onUpgrade");
            database.execSQL("create table if not exists tmp ("
                    + KEY_ID + " integer not null primary key autoincrement,"
                    + KEY_F + " text,"
                    + KEY_I + " text,"
                    + KEY_O + " text,"
                    + KEY_TIME + " text" + ");");
            Cursor c = database.rawQuery("select id, fio, time from " + TABLE_STUDENTS, null);
            ContentValues cv = new ContentValues();
            if (c.moveToFirst()){
                do {
                    cv.clear();
                    cv.put("id", c.getInt(0));
                    String FIO = c.getString(1);
                    cv.put("f", FIO.substring(0, FIO.indexOf(' ')));
                    cv.put("i", FIO.substring(FIO.indexOf(' ')+1, FIO.substring(FIO.indexOf(' ')+1).indexOf(' ') + FIO.indexOf(' ')+1));
                    cv.put("o", FIO.substring(FIO.substring(FIO.indexOf(' ')+1).indexOf(' ') + FIO.indexOf(' ')+2));
                    cv.put("Time", c.getString(2));
                    database.insert("tmp", null, cv);
                }while (c.moveToNext());
            }
            database.execSQL("drop table if exists " + TABLE_STUDENTS);
            database.execSQL("alter table tmp rename to "  + TABLE_STUDENTS);
            c.close();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.i ("my", "onDowngrade");
        if (oldVersion == 2 && newVersion == 1){
            oldVersion++;
            database.execSQL("create table if not exists tmp ("
                    + KEY_ID + " integer not null primary key autoincrement,"
                    + KEY_FIO + " text,"
                    + KEY_TIME + " text" + ");");
            Cursor c = database.rawQuery("select id, f, i, o, time from students", null);
            ContentValues cv = new ContentValues();
            if (c.moveToFirst()){
                do {
                    cv.clear();
                    cv.put("id", c.getInt(0));
                    String FIO = c.getString(1) + " " + c.getString(2)  + " " +  c.getString(3);
                    cv.put("fio", FIO);
                    cv.put("time", c.getString(4));
                    database.insert("Students2", null, cv);
                }while (c.moveToNext());
            }

            database.execSQL("drop table if exists " + TABLE_STUDENTS);
            database.execSQL("alter table tmp rename to "  + TABLE_STUDENTS);
            c.close();
        }
    }
}
