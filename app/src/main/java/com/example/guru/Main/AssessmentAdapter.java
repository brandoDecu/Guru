package com.example.guru.Main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.guru.Assessment.ViewAssessmentActivity;
import com.example.guru.Model.AssessmentDataStruct;
import com.example.guru.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static com.example.guru.ApplicationClass.USER_NAME_SHAREDPREFS;
import static com.example.guru.Assessment.AssessmentActivity.ASSESSMENT_JSON;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private MainActivity mainActivity;
    private List<AssessmentDataStruct> assessmentList = new ArrayList<AssessmentDataStruct>();
    private static final String TAG = "AssessmentAdapter";
    private static String[] months = {"January", "February", "March", "April", "May", "June", "July",
    "August", "September" , "October", "November", "December"};


    AssessmentAdapter(Context context) {
        mainActivity = (MainActivity) context;


    }

    public void setAssessmentList(List<AssessmentDataStruct> assessmentList) {
        this.assessmentList = assessmentList;
        notifyDataSetChanged();
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        TextView date_tv, name_tv;
        TextView largest_text_tv, largest_text2_tv;
        LinearLayout largest_text_layout;
        ImageView upload;
        ProgressBar progressBar;





        AssessmentViewHolder(@NonNull final View itemView) {
            super(itemView);
            date_tv = itemView.findViewById(R.id.assessment_date_tv);
            name_tv = itemView.findViewById(R.id.name_textview);
            largest_text_tv = itemView.findViewById(R.id.largest_text_tv);
            largest_text2_tv = itemView.findViewById(R.id.largest_text2_tv);
            largest_text_layout = itemView.findViewById(R.id.largest_text_layout);
            progressBar = itemView.findViewById(R.id.progressBar);
            upload = itemView.findViewById(R.id.upload_ib);
            name_tv.setText(mainActivity.getPrefs().getString(USER_NAME_SHAREDPREFS, ""));


            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AssessmentDataStruct assessment = assessmentList.get(getAdapterPosition());
                    if (assessment.isUploaded()) {
                        Toast.makeText(mainActivity, "Already on the cloud", Toast.LENGTH_SHORT).show();
                    } else {
                        assessment.setUploaded(true);
                        showProgress(true);
                        Backendless.Data.save(assessment, new AsyncCallback<AssessmentDataStruct>() {
                            @Override
                            public void handleResponse(AssessmentDataStruct response) {
                                Toast.makeText(mainActivity, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                                mainActivity.assessmentFragment.getViewModel().update(response);
                                upload.setImageResource(R.drawable.check_32dp);
                                showProgress(false);

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                String error = mainActivity.getResources().getString(R.string.error);
                                Toast.makeText(mainActivity, error + ": " + fault.getMessage(),
                                        Toast.LENGTH_LONG).show();
                                assessment.setUploaded(false);
                                showProgress(false);
                            }
                        });
                    }


                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(mainActivity, ViewAssessmentActivity.class);
                    AssessmentDataStruct assessment = assessmentList.get(position);
                    Log.d(TAG, "viewing assessment... " + assessment.toString());
                    Gson gson = new Gson();
                    String json = gson.toJson(assessment);
                    intent.putExtra(ASSESSMENT_JSON,json);
                    mainActivity.startActivity(intent);
                }
            });



            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int position = getAdapterPosition();
                    AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                    builder.setMessage(R.string.delete_assessment)
                            .setCancelable(true)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (assessmentList.get(position).isUploaded()) {
                                        showProgress(true);
                                        Backendless.Persistence.of(AssessmentDataStruct.class).remove(assessmentList.get(position), new AsyncCallback<Long>() {
                                            @Override
                                            public void handleResponse(Long response) {
                                                showProgress(false);
                                                mainActivity.getViewModel().delete(assessmentList.get(position));
                                                Toast.makeText(mainActivity, "Deleted", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void handleFault(BackendlessFault fault) {
                                                String error = mainActivity.getResources().getString(R.string.error);
                                                Toast.makeText(mainActivity, error + " file on the cloud cannot be deleted: " + fault.getMessage(),
                                                        Toast.LENGTH_LONG).show();
                                                showProgress(false);
                                            }
                                        });
                                    } else {
                                        mainActivity.getViewModel().delete(assessmentList.get(position));
                                    }

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



                    return true;
                }
            });
        }

        private void showProgress(boolean b) {
            if (b) {
                progressBar.setVisibility(View.VISIBLE);
                upload.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
                upload.setVisibility(View.VISIBLE);
            }
        }
    }





    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.assessment_item, viewGroup, false);
        return new AssessmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder viewHolder, int i) {
        AssessmentDataStruct currentAssessment = assessmentList.get(i);
        viewHolder.itemView.setTag(currentAssessment.toString());
        Calendar cal = new GregorianCalendar();
        cal.setTime(currentAssessment.getJavaDateCreated());
        String month = months[cal.get(Calendar.MONTH)];
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);

        String displayDate = month + " " + day_of_month + " " + year;

        viewHolder.date_tv.setText(displayDate);
        if (currentAssessment.getSecondLongestText() == null || currentAssessment.getSecondLongestText().isEmpty()) {
            viewHolder.largest_text2_tv.setVisibility(View.GONE);
        } else {
            viewHolder.largest_text2_tv.setVisibility(View.VISIBLE);
        }
        if (currentAssessment.getLongestText() == null || currentAssessment.getLongestText().isEmpty()) {
            viewHolder.largest_text_tv.setVisibility(View.GONE);
        } else {
            viewHolder.largest_text_tv.setVisibility(View.VISIBLE);
        }
        viewHolder.largest_text_tv.setText(currentAssessment.getLongestText());
        viewHolder.largest_text2_tv.setText(currentAssessment.getSecondLongestText());

        if (currentAssessment.isUploaded()) {
            viewHolder.upload.setImageResource(R.drawable.check_32dp);
        }





    }

    @Override
    public int getItemCount() {
        return assessmentList.size();
    }



}
