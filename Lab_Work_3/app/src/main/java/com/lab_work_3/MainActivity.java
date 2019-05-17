package com.lab_work_3;

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

    private Button btnShowAll;
    private Button btnAddNew;
    private Button btnShowLast;

    private ArrayList<String> names;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowAll = (Button) findViewById(R.id.btnShowAll);
        btnAddNew = (Button) findViewById(R.id.btnAddNew);
        btnShowLast = (Button) findViewById(R.id.btnShowLast);
        names = new ArrayList<>();
        dbHelper = new DBHelper(this);

        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int clearCount = database.delete(DBHelper.TABLE_STUDENTS, null, null);
        Log.e("some", "Очистка базы: " + String.valueOf(clearCount));

        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        String sDate = sdfDate.format(now);

        for (int i = 0; i < 5; i++) {
            contentValues.put(DBHelper.KEY_FIO, RandomNames.getRandFio());
            contentValues.put(DBHelper.KEY_TIME, sDate);
            database.insert(DBHelper.TABLE_STUDENTS, null, contentValues);
        }

        // Событие нажатия на кнопку "Показать всех"
        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbHelper.getReadableDatabase();
                Cursor cursor = database.query(DBHelper.TABLE_STUDENTS, null, null, null, null, null, null);

                Log.e("some", "Показать всех");
                names = new ArrayList<>();
                int fioIndex = cursor.getColumnIndex(DBHelper.KEY_FIO);
                int timeIndex = cursor.getColumnIndex(DBHelper.KEY_TIME);

                if (cursor.moveToFirst()) {
                    do {
                        names.add(String.valueOf(names.size() + 1) + ": " + cursor.getString(fioIndex) + " - " + cursor.getString(timeIndex));
                    } while (cursor.moveToNext());
                }
                cursor.close();
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("names", names);
                startActivity(intent);
            }
        });

        // Событие нажатия на кнопку "Добавление нового"
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");//dd/MM/yyyy
                Date now = new Date();
                String sDate = sdfDate.format(now);
                contentValues.put(DBHelper.KEY_FIO, RandomNames.getRandFio());
                contentValues.put(DBHelper.KEY_TIME, sDate);
                long i = database.insert(DBHelper.TABLE_STUDENTS, null, contentValues);
                Log.e("some", "Добавление нового: " + String.valueOf(i));
            }
        });

        // Событие нажатия на кнопку "Заменить последнего"
        btnShowLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("some", "Замена последнего");
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");//dd/MM/yyyy
                Date now = new Date();
                String sDate = sdfDate.format(now);
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.KEY_FIO, RandomNames.getIvan());
                contentValues.put(DBHelper.KEY_TIME, sDate);

                Cursor cursor = database.query(DBHelper.TABLE_STUDENTS, null, null, null, null, null, null);
                int id = 0;
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                if (cursor.moveToFirst()) {
                    do {
                        if (id < cursor.getInt(idIndex))
                            id = cursor.getInt(idIndex);
                    } while (cursor.moveToNext());
                }
                cursor.close();

                database.update(DBHelper.TABLE_STUDENTS,contentValues,DBHelper.KEY_ID + " = " + String.valueOf(id), null);
            }
        });
    }
}