package com.example.guru.Assessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.guru.Main.AssessmentAdapter;
import com.example.guru.Model.AssessmentDataStruct;
import com.example.guru.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static com.example.guru.Assessment.AssessmentActivity.ASSESSMENT_JSON;

public class ViewAssessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String json = intent.getStringExtra(ASSESSMENT_JSON);
        Type type = new TypeToken<AssessmentDataStruct>() {}.getType();
        Gson gson = new Gson();
        AssessmentDataStruct assessment = gson.fromJson(json, type);
        setContentView(R.layout.activity_view_assessment);
        RecyclerView recyclerView = findViewById(R.id.questions_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        final QuestionAdapter adapter = new QuestionAdapter(assessment,
                getResources().obtainTypedArray(R.array.all_assessment_strings), this);
        recyclerView.setAdapter(adapter);
    }
}
