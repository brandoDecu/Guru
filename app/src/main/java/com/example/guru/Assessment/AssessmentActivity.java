package com.example.guru.Assessment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guru.Model.AssessmentDataStruct;
import com.example.guru.R;
import com.google.gson.Gson;

import java.util.Date;

import static com.example.guru.ApplicationClass.GURU_USER_LOGGED_OUT_SHAREDPREFS;
import static com.example.guru.ApplicationClass.USER_FILENAME_SHAREDPREFS;

public class AssessmentActivity extends AppCompatActivity {

    public static final String ASSESSMENT_JSON = "com.example.guru.Assessment_ASSESSMENT_JSON";
    public final int[] layouts = {R.layout.assessment_layout1, R.layout.assessment_layout2,
            R.layout.assessment_layout3, R.layout.assessment_layout4, R.layout.assessment_layout_questions};
    private ViewPager viewPager;
    private Button prev, next;
    private static final String TAG = "AssessmentActivity";

    public final int[][] questionTitles1 = {
            {R.string.five_states, R.string.five_states1, R.string.five_states2, R.string.five_states3,
                    R.string.five_states4, R.string.five_states5},

            {R.string.five_efforts, R.string.five_efforts1, R.string.five_efforts2, R.string.five_efforts3,
                    R.string.five_efforts4, R.string.five_efforts5},

            {R.string.four_attitudes, R.string.four_attitudes1, R.string.four_attitudes2,
                    R.string.four_attitudes3, R.string.four_attitudes4},

            {R.string.four_functions_of_mind, R.string.four_functions_of_mind1, R.string.four_functions_of_mind2,
                    R.string.four_functions_of_mind3, R.string.four_functions_of_mind4},

            {R.string.four_primitive_urges, R.string.four_primitive_urges1, R.string.four_primitive_urges2,
                    R.string.four_primitive_urges3, R.string.four_primitive_urges4},

            {R.string.jnanendriyas, R.string.jnanendriyas1, R.string.jnanendriyas2,
                    R.string.jnanendriyas3, R.string.jnanendriyas4, R.string.jnanendriyas5},
            {R.string.karmendriyas, R.string.karmendriyas1, R.string.karmendriyas2,
                    R.string.karmendriyas3, R.string.karmendriyas4, R.string.karmendriyas5},

            {R.string.five_vayus, R.string.five_vayus1, R.string.five_vayus2, R.string.five_vayus3,
                    R.string.five_vayus4, R.string.five_vayus5},

            {R.string.five_elements, R.string.five_elements1, R.string.five_elements2,
                    R.string.five_elements3, R.string.five_elements4, R.string.five_elements5},

            {R.string.three_gunas, R.string.three_gunas1, R.string.three_gunas2,
                    R.string.three_gunas3},

            {R.string.circle_chart, R.string.circle_chart1, R.string.circle_chart2,
                    R.string.circle_chart3, R.string.circle_chart4, R.string.circle_chart5,
                    R.string.circle_chart6, R.string.circle_chart7, R.string.circle_chart8},

            {R.string.five_kinds_of_thoughts, R.string.five_kinds_of_thoughts1,
                    R.string.five_kinds_of_thoughts2, R.string.five_kinds_of_thoughts3,
                    R.string.five_kinds_of_thoughts4, R.string.five_kinds_of_thoughts5},

            {R.string.five_kleshas, R.string.five_kleshas1, R.string.five_kleshas2,
                    R.string.five_kleshas3, R.string.five_kleshas4, R.string.five_kleshas5},

            {R.string.four_stages_of_kleshas, R.string.four_stages_of_kleshas1,
                    R.string.four_stages_of_kleshas2, R.string.four_stages_of_kleshas3,
                    R.string.four_stages_of_kleshas4},

            {R.string.four_kinds_of_avidya, R.string.four_kinds_of_avidya1,
                    R.string.four_kinds_of_avidya2, R.string.four_kinds_of_avidya3,
                    R.string.four_kinds_of_avidya4},

            {R.string.eight_rungs_of_yoga, R.string.eight_rungs_of_yoga1, R.string.eight_rungs_of_yoga2,
                    R.string.eight_rungs_of_yoga3, R.string.eight_rungs_of_yoga4,
                    R.string.eight_rungs_of_yoga5, R.string.eight_rungs_of_yoga6,
                    R.string.eight_rungs_of_yoga7, R.string.eight_rungs_of_yoga8},

            {R.string.five_yamas, R.string.five_yamas1, R.string.five_yamas2, R.string.five_yamas3,
                    R.string.five_yamas4, R.string.five_yamas5},

            {R.string.five_niyamas, R.string.five_niyamas1, R.string.five_niyamas2,
                    R.string.five_niyamas3, R.string.five_niyamas4, R.string.five_niyamas5},

            {R.string.seven_chakras, R.string.seven_chakras1, R.string.seven_chakras2,
                    R.string.seven_chakras3, R.string.seven_chakras4, R.string.seven_chakras5},

            {R.string.source_of_karma, R.string.source_of_karma1, R.string.source_of_karma2,
                    R.string.source_of_karma3, R.string.source_of_karma4, R.string.source_of_karma5},

            {R.string.doshas, R.string.doshas1, R.string.doshas2, R.string.doshas3},

            {R.string.overall_thoughts},

            {R.string.assessment_completed}};

    private int nTabs;
    public AssessmentDataStruct assessment;
    private AssessmentPageFragment currentFragment;
    private TypedArray questionTitles;


    public TypedArray getQuestionTitles() {
        return questionTitles;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Intent intent = getIntent();
//        int theme = intent.getIntExtra("theme", 0);
//        if (theme != 0) {
//            setTheme(theme);
//        }

        questionTitles = getResources().obtainTypedArray(R.array.all_assessment_strings);
        nTabs = layouts.length - 1 + questionTitles.length();
        CharSequence[] asd = questionTitles.getTextArray(0);
        Toast.makeText(this, asd[0], Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment);

        viewPager = findViewById(R.id.view_pager);
        AssessmentPagerAdapter pagerAdapter = new AssessmentPagerAdapter(this,
                getSupportFragmentManager(), nTabs);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        prev = findViewById(R.id.previous_btn);
        next = findViewById(R.id.next_btn);
        setSkipIntroBtn();
        setNextBtn();
        assessment = new AssessmentDataStruct();
        SharedPreferences prefs = getSharedPreferences(USER_FILENAME_SHAREDPREFS, MODE_PRIVATE);
        String email = prefs.getString("email", "");
        if (email.isEmpty() || email.equals(GURU_USER_LOGGED_OUT_SHAREDPREFS)) {
            Toast.makeText(this, "No User is logged in", Toast.LENGTH_LONG).show();
            onBackPressed();
        } else {
            assessment.setUserEmail(email);
            assessment.setJavaDateCreated(new Date());
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    setSkipIntroBtn();
                } else {
                    setPrevBtn();
                }
                if (i == nTabs - 1) {
                    setSaveBtn();
                } else {
                    setNextBtn();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });



    }

    public AssessmentPageFragment getAssessmentFragment(int i) {
        AssessmentPageFragment frag = new AssessmentPageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page", i);
        frag.setArguments(bundle);
        currentFragment = frag;
        return frag;
    }

    public void setSkipIntroBtn() {
        Log.d(TAG, "setSkipIntroBtn");
        prev.setText(R.string.skip_intro);
        prev.setTypeface(prev.getTypeface(), Typeface.BOLD);
        prev.setTextSize(18);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(layouts.length - 1);
            }
        });


    }

    public void setPrevBtn() {
        Log.d(TAG, "setPrevBtn");
        prev.setText(R.string.previous);
        prev.setTypeface(prev.getTypeface(), Typeface.NORMAL);
        prev.setTextSize(14);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });
    }

    public void setSaveBtn() {
        Log.d(TAG, "setSaveBtn");
        next.setText(R.string.save);
        next.setTypeface(next.getTypeface(), Typeface.BOLD);
        next.setTextSize(18);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assessment.setLongestTexts();
                saveAssessment(assessment);
            }


        });
    }

    public void setNextBtn() {
        Log.d(TAG, "setNextBtn");
        next.setText(R.string.next);
        next.setTypeface(next.getTypeface(), Typeface.NORMAL);
        next.setTextSize(14);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

            }
        });
    }


    private void saveAssessment(AssessmentDataStruct assessment) {
        Intent returnIntent = new Intent();
        Log.d(TAG, "returning to main activity... " + assessment.toString());
        Gson gson = new Gson();
        String json = gson.toJson(assessment);
        returnIntent.putExtra(ASSESSMENT_JSON,json);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.exit_without_saving_assignment)
                .setCancelable(true)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AssessmentActivity.super.onBackPressed();
                    }
                })
        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}