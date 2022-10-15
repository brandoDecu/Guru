package com.example.guru.Assessment;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guru.Main.AssessmentAdapter;
import com.example.guru.Model.AssessmentDataStruct;
import com.example.guru.R;

import java.lang.reflect.Type;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private final AssessmentDataStruct assessment;
    private final TypedArray allquestions;
    private final Activity activity;

    public QuestionAdapter(AssessmentDataStruct assessment, TypedArray allquestions, Activity activity) {
        this.assessment = assessment;
        this.allquestions = allquestions;
        this.activity = activity;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.question_tv.setText(allquestions.getTextArray(position)[0]);
        int option = assessment.getOptionAt(position);
        String text = assessment.getTextAt(position);

        if (option == -1 && text.isEmpty()) {
            holder.main_layout.setVisibility(View.GONE);

        } else {

            if (option == -1) {
                holder.choice_tv.setText("-");
            } else {
                holder.choice_tv.setText(allquestions.getTextArray(position)[option + 1]);
            }

            if (text.isEmpty()) {
                holder.text_tv.setVisibility(View.GONE);
            } else {
                holder.text_tv.setText(text);
            }


        }







    }

    @Override
    public int getItemCount() {
        return allquestions.length() - 1;
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView question_tv, choice_tv, text_tv;
        LinearLayout main_layout;


        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            question_tv = itemView.findViewById(R.id.question_tv);
            choice_tv = itemView.findViewById(R.id.choice_tv);
            text_tv = itemView.findViewById(R.id.text_tv);
            main_layout = itemView.findViewById(R.id.main_layout);
        }
    }
}
