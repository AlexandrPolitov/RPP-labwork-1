package com.lab_work;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import android.os.Bundle;
import android.content.Intent;

// Ссылка на картинки
// https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/....

// Ссылка на Json
// https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ItemClickListener {

    public static RecyclerAdapter listAdapter;
    private RecyclerView recyclerView;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создание и заполнение списка
        initRecycler();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, ViewPageActivity.class);
        startActivity(intent);
    }

    void initRecycler(){
        names = new ArrayList<>();
        names.add("Horse");
        names.add("Cow");
        names.add("Camel");
        names.add("Sheep");
        names.add("Goat");

        recyclerView = (RecyclerView) findViewById(R.id.list_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listAdapter = new RecyclerAdapter(this, names);
        listAdapter.setClickListener(this);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);
    }
}
