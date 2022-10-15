package com.example.guru.Assessment;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.util.Log;

public class AssessmentPagerAdapter extends FragmentPagerAdapter {
    private int nTabs;
    private static final String TAG = "PagerAdapter";
    private FragmentManager fm;
    private AssessmentActivity assessmentActivity;

    public AssessmentPagerAdapter(Context context, FragmentManager fm, int nTabs) {
        super(fm);
        this.fm = fm;
        this.nTabs = nTabs;
        Log.d(TAG, "ntabs:" + nTabs);
        assessmentActivity = (AssessmentActivity) context;
    }

    @Override
    public Fragment getItem(int position) {
        return assessmentActivity.getAssessmentFragment(position);
    }



    @Override
    public int getCount() {
        return nTabs;
    }



}
