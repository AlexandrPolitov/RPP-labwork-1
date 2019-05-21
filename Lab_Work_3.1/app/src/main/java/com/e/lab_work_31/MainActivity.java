package com.e.lab_work_31;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private boolean created = false;

    private Button btnShowAll;
    private ArrayList<String> names;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowAll = (Button) findViewById(R.id.button);
        names = new ArrayList<>();
        dbHelper = new DbHelper(this);
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        database.delete(DbHelper.TABLE_STUDENTS, null, null);
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        String sDate = sdfDate.format(now);

        if (savedInstanceState != null)
            created = savedInstanceState.getBoolean("created");
        if (!created) {
            if (dbHelper.DATABASE_VERSION == 2)
                for (int i = 0; i < 5; i++) {
                    contentValues.put(DbHelper.KEY_F, RndNames.getRandF());
                    contentValues.put(DbHelper.KEY_I, RndNames.getRandI());
                    contentValues.put(DbHelper.KEY_O, RndNames.getRandO());
                    contentValues.put(DbHelper.KEY_TIME, sDate);
                    database.insert(DbHelper.TABLE_STUDENTS, null, contentValues);
                }
            else
                for (int i = 0; i < 5; i++) {
                    contentValues.put(DbHelper.KEY_FIO, RndNames.getRandFio());
                    contentValues.put(DbHelper.KEY_TIME, sDate);
                    database.insert(DbHelper.TABLE_STUDENTS, null, contentValues);
                }
            created = true;
        }
            btnShowAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase database = dbHelper.getReadableDatabase();
                    Cursor cursor = database.query(DbHelper.TABLE_STUDENTS, null, null, null, null, null, null);

                    Log.e("my", "Показать всех");
                    names = new ArrayList<>();

                    int timeIndex = cursor.getColumnIndex(DbHelper.KEY_TIME);
                    if (DbHelper.DATABASE_VERSION == 2) {

                        int fIndex = cursor.getColumnIndex(DbHelper.KEY_F);
                        int iIndex = cursor.getColumnIndex(DbHelper.KEY_I);
                        int oIndex = cursor.getColumnIndex(DbHelper.KEY_O);
                        if (cursor.moveToFirst()) {
                            do {
                                names.add(String.valueOf(names.size() + 1) + ": " + cursor.getString(fIndex) + "_" + cursor.getString(iIndex) + "_" + cursor.getString(oIndex) + " - " + cursor.getString(timeIndex));
                            } while (cursor.moveToNext());
                        }
                        cursor.close();
                    }
                    else {
                        int fIndex = cursor.getColumnIndex(DbHelper.KEY_FIO);
                        if (cursor.moveToFirst()) {
                            do {
                                names.add(String.valueOf(names.size() + 1) + ": " + cursor.getString(fIndex) + " - " + cursor.getString(timeIndex));
                            } while (cursor.moveToNext());
                        }
                        cursor.close();
                    }
                    Log.e("some", "Показать всех");
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("names", names);
                    startActivity(intent);
                }
            });
        }
    }


