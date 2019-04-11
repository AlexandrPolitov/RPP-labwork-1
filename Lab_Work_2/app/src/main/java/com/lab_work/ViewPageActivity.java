package com.lab_work;

import android.os.Bundle;
import java.util.ArrayList;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPageActivity extends AppCompatActivity {

    public ArrayList<CivilizationItem> items;
    private int position;
    private ViewPager viewPager;
    private PageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        // Получение списка и позиции нажатия
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        items = (ArrayList<CivilizationItem>)intent.getSerializableExtra("list");
        //----------------------------------

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

    // Класс Adapter для ViewPager
    private class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager frag_manager) {
            super(frag_manager);
        }

        @Override
        public Fragment getItem(int position) {
            return MoreInfoFragment.newInstance(position, items);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
