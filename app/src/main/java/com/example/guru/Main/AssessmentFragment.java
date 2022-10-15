package com.example.guru.Main;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.FrameLayout;

import com.example.guru.Model.AssessmentDataStruct;
import com.example.guru.R;
import com.example.guru.ViewModel.AssessmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AssessmentFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private View view;
    private FrameLayout new_assessment_layout;
    private AssessmentViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;


    public AssessmentFragment() {
        // Required empty public constructor
    }


    public AssessmentViewModel getViewModel() {
        return viewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_assessment, container, false);
        new_assessment_layout = view.findViewById(R.id.new_assessment_layout);
//        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(true);
//                new Handler().postDelayed(new Runnable() {
//                    @Override public void run() {
//                        recyclerView.setAdapter(new AssessmentAdapter(getActivity()));
//                        if (!Model.getInstance().getAssessmentList().isEmpty()) {
//                            new_assessment_layout.setVisibility(View.GONE);
//                        }
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 3000);
//
//
//
//            }
//        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.assessment_list);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        final AssessmentAdapter adapter = new AssessmentAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        FloatingActionButton actionButton = view.findViewById(R.id.floatingActionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.startAssessment();
            }
        });
        viewModel = ViewModelProviders.of(this).get(AssessmentViewModel.class);
        viewModel.getAllAssessments().observe(this, new Observer<List<AssessmentDataStruct>>() {
            @Override
            public void onChanged(@Nullable List<AssessmentDataStruct> assessmentDataStructs) {
                adapter.setAssessmentList(assessmentDataStructs);
                if (!assessmentDataStructs.isEmpty()) {
                    new_assessment_layout.setVisibility(View.GONE);
                } else {
                    new_assessment_layout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

//    public void onAssessmentAdded() {
//        recyclerView.getAdapter().notifyItemInserted(0);
//        recyclerView.scrollToPosition(0);
//        if (!Model.getInstance().getAssessmentList().isEmpty()) {
//            new_assessment_layout.setVisibility(View.GONE);
//        }
//    }
//
//    public void onAssessmentRemoved(int index) {
//
////        recyclerView.removeViewAt(index);
//        recyclerView.getAdapter().notifyItemRemoved(index);
////        recyclerView.getAdapter().notifyItemRangeChanged(index, recyclerView.getAdapter().getItemCount());
////        recyclerView.getAdapter().notifyDataSetChanged();
//        if (Model.getInstance().getAssessmentList().isEmpty()) {
//            new_assessment_layout.setVisibility(View.VISIBLE);
//        }
//    }



}
