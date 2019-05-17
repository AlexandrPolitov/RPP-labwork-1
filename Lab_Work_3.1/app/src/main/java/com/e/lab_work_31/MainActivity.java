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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbHelper.getReadableDatabase();
                Cursor cursor = database.query(DbHelper.TABLE_STUDENTS, null, null, null, null, null, null);

                Log.e("some", "Показать всех");
                names = new ArrayList<>();
                int fioIndex = cursor.getColumnIndex("fio");
                int timeIndex = cursor.getColumnIndex(DbHelper.KEY_TIME);
                Log.e("some", "Показать всех");
                if (cursor.moveToFirst()) {
                    do {
                        names.add(String.valueOf(names.size() + 1) + ": " + cursor.getString(fioIndex) + " - " + cursor.getString(timeIndex));
                    } while (cursor.moveToNext());
                }
                cursor.close();
                Log.e("some", "Показать всех");
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("names", names);
                startActivity(intent);
            }
        });
    }
}
