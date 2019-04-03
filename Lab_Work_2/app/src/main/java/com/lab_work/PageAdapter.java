package com.lab_work;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    static final int PAGE_COUNT = 2;

    public PageAdapter(FragmentManager frag_manager) {
        super(frag_manager);
    }

    @Override
    public Fragment getItem(int position) {
       return null;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
