package com.example.guru.Main;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PagerAdapter extends FragmentPagerAdapter {


    private int nTabs;
    private static final String TAG = "PagerAdapter";
    private FragmentManager fm;
    private MainActivity mainActivity;

    public PagerAdapter(Context context, FragmentManager fm, int nTabs) {
        super(fm);
        this.fm = fm;
        this.nTabs = nTabs;
        mainActivity = (MainActivity) context;
    }

    @Override
    public Fragment getItem(int position) {
        return mainActivity.fragments[position];
    }



    @Override
    public int getCount() {
        return nTabs;
    }


}