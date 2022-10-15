package com.example.guru.Main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.guru.ViewModel.AssessmentViewModel;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import com.example.guru.Assessment.AssessmentActivity;
import com.example.guru.Model.AssessmentDataStruct;
import com.example.guru.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static com.example.guru.ApplicationClass.USER_FILENAME_SHAREDPREFS;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private int[] menuIcons;
    private int[] unselectedMenuIcons;
    private int[] titles;
    Fragment[] fragments;

    AssessmentFragment assessmentFragment;
    TrackFragment trackFragment;
    private SharedPreferences prefs;

    public SharedPreferences getPrefs() {
        return prefs;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefs = getSharedPreferences(USER_FILENAME_SHAREDPREFS, MODE_PRIVATE);
        setTitle("");




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assessmentFragment = new AssessmentFragment();
        trackFragment = new TrackFragment();
        fragments = new Fragment[] {new HomeFragment(),  assessmentFragment, trackFragment, new SettingsFragment()};
        menuIcons = new int[] {R.drawable.menu_home_24dp, R.drawable.menu_saa_24dp,
                R.drawable.menu_music_24dp, R.drawable.menu_settings_24dp};
        unselectedMenuIcons = new int[] {R.drawable.menu_home_unselected_24dp,
                R.drawable.menu_saa_unselected_24dp, R.drawable.menu_music_unselected_24dp,
                R.drawable.menu_settings_unselected_24dp};


        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager = findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setIcon(menuIcons[0]));
        for (int i = 1; i < menuIcons.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setIcon(unselectedMenuIcons[i]));
        }

        final PagerAdapter pagerAdapter = new PagerAdapter(this,
                getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
//                setTitle(titles[tab.getPosition()]);


                for (int j = 0; j < tabLayout.getTabCount(); j++) {
                    if (tab.getPosition() == j) {
                        tabLayout.getTabAt(j).setIcon(menuIcons[j]);
                    } else {
                        tabLayout.getTabAt(j).setIcon(unselectedMenuIcons[j]);
                    }

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public AssessmentViewModel getViewModel() {
        return assessmentFragment.getViewModel();
    }


    // Assessment //

    public void startAssessment() {
        Intent intent = new Intent(this, AssessmentActivity.class);
        startActivityForResult(intent, 420);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 420) {
            if(resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "assessment Returned");
                String json = data.getStringExtra(AssessmentActivity.ASSESSMENT_JSON);
                Type type = new TypeToken<AssessmentDataStruct>() {}.getType();
                Gson gson = new Gson();
                AssessmentDataStruct assessment = gson.fromJson(json, type);
                Log.d(TAG, "Returned " + assessment.toString());


                getViewModel().insert(assessment);

            }

        }
    }

}