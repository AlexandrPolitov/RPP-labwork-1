package com.lab_work;

import retrofit2.Call;
import java.util.List;
import android.view.View;
import android.os.Bundle;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.ArrayList;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ItemClickListener {

    private static final String URL_JSON = "https://raw.githubusercontent.com";
    private NetInterface netInterface;
    private Retrofit retrofit;
    private ArrayList<CivilizationItem> items = new ArrayList<>();
    private static RecyclerAdapter listAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycler();
    }

    @Override
    public void onItemClick(View view, int position) {
        // Создание ViewPageActivity и передача туда списка и позиции нажатого элемента
        Intent intent = new Intent(MainActivity.this, ViewPageActivity.class);
        intent.putExtra("list", items);
        intent.putExtra("position", position);
        startActivity(intent);
        ///////////////////////////////////////////////////////////////////////////////
    }

    void initRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.list_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listAdapter = new RecyclerAdapter(this, items);
        listAdapter.setClickListener(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

        retrofit = new Retrofit.Builder().baseUrl(URL_JSON)
                .addConverterFactory(GsonConverterFactory.create()).build();
        netInterface = retrofit.create(NetInterface.class);

        netInterface.getData().enqueue(new Callback<List<CivilizationItem>>() {
            @Override
            public void onResponse(Call<List<CivilizationItem>> call, Response<List<CivilizationItem>> response) {
                items.addAll(response.body());
                items.remove(0);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<CivilizationItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Please, check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}