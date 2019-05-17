package com.e.lab_work_31;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "lab3DB";
    public static final String TABLE_STUDENTS = "students";

    public static final String KEY_ID = "id";
  //  public static final String KEY_F = "f";
    //public static final String KEY_I = "i";
    //public static final String KEY_O = "o";
    public static final String KEY_TIME = "time";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL("create table " + TABLE_STUDENTS + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + "fio" + " text,"
                + KEY_TIME + " text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
       /* String oldTable = "TempOldTable";
        database.execSQL( "alter table " + TABLE_STUDENTS + " rename to " + oldTable + " ;");
        onCreate(database);

        Cursor cursor = database.query(oldTable, null, null, null, null, null, null);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();
        int fioIndex = cursor.getColumnIndex("fio");
        int timeIndex = cursor.getColumnIndex(KEY_TIME);

        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(fioIndex));
                times.add(cursor.getString(timeIndex));
            } while (cursor.moveToNext());
        }
        cursor.close();
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < names.size(); i++) {
            StringTokenizer tokenizer = new StringTokenizer(names.get(i));
            contentValues.put(KEY_F, tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "");
            contentValues.put(KEY_I, tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "");
            contentValues.put(KEY_O, tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "");
            contentValues.put(KEY_TIME, times.get(i));
            database.insert(TABLE_STUDENTS, null, contentValues);
        }*/
    }
}
