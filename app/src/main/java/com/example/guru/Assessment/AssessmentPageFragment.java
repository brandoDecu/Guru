package com.example.guru.Assessment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.guru.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssessmentPageFragment extends Fragment {
    private static final String TAG = "AssessmentPageFragment";
    private EditText editText;
    private AssessmentActivity assessmentActivity;
    private int quizpageNumber;


    public AssessmentPageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        assessmentActivity = (AssessmentActivity) getActivity();
        int position = arguments.getInt("page", 0);
        boolean quizpage = false;
        quizpageNumber = position - assessmentActivity.layouts.length + 1;
        if (position >= assessmentActivity.layouts.length - 1) {
            quizpage = true;

            position = assessmentActivity.layouts.length - 1;
        }
        Log.d(TAG, "layout: " + position);
        View view = inflater.inflate(assessmentActivity.layouts[position], container, false);
        if (quizpage) {
            Log.d(TAG, "quizpage");
            TextView questions_tv = view.findViewById(R.id.question_tv);
            RadioButton rb1 = view.findViewById(R.id.radiobtn1);
            RadioButton rb2 = view.findViewById(R.id.radiobtn2);
            RadioButton rb3 = view.findViewById(R.id.radiobtn3);
            RadioButton rb4 = view.findViewById(R.id.radiobtn4);
            RadioButton rb5 = view.findViewById(R.id.radiobtn5);
            RadioButton rb6 = view.findViewById(R.id.radiobtn6);
            RadioButton rb7 = view.findViewById(R.id.radiobtn7);
            RadioButton rb8 = view.findViewById(R.id.radiobtn8);
            RadioGroup radioGroup = view.findViewById(R.id.radiogroup);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int i = -1;
                    switch (checkedId) {
                        case R.id.radiobtn1:
                            i = 0;
                            break;
                        case R.id.radiobtn2:
                            i = 1;
                            break;
                        case R.id.radiobtn3:
                            i = 2;
                            break;
                        case R.id.radiobtn4:
                            i = 3;
                            break;
                        case R.id.radiobtn5:
                            i = 4;
                            break;
                        case R.id.radiobtn6:
                            i = 5;
                            break;
                        case R.id.radiobtn7:
                            i = 6;
                            break;
                        case R.id.radiobtn8:
                            i = 7;
                            break;
                    }
                    assessmentActivity.assessment.setOptionAt(quizpageNumber, i);
                }
            });
            RadioButton[] rbs = {rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8};
            CharSequence[] questionStrings = assessmentActivity.getQuestionTitles().getTextArray(quizpageNumber);

            String pageNumber = "" + (quizpageNumber + 1) + " of " + assessmentActivity.getQuestionTitles().length();
            TextView page_number_tv = view.findViewById(R.id.page_number_tv);
            page_number_tv.setText(pageNumber);
            questions_tv.setText(questionStrings[0]);
            for (int i = 0; i < rbs.length; i++) {
                if (i < questionStrings.length - 1) {
                    rbs[i].setText(questionStrings[i+1]);
                } else {
                    rbs[i].setVisibility(View.GONE);
                }
            }
            editText = view.findViewById(R.id.anythoughts_et);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    saveText();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if (quizpageNumber == assessmentActivity.getQuestionTitles().length() - 1) {
                editText.setVisibility(View.GONE);
            }



        }

        return view;
    }

    public void saveText() {
        String text = editText.getText().toString().trim();
        if (!text.isEmpty()) {
            assessmentActivity.assessment.setTextAt(quizpageNumber, text);
        }
    }

}
