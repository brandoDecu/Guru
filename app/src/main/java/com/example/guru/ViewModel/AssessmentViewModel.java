package com.example.guru.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.guru.Model.AssessmentDataStruct;
import com.example.guru.Model.Repository;

import java.util.List;


public class AssessmentViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<AssessmentDataStruct>> allAssessments;

    public AssessmentViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allAssessments = repository.getAssessmentList();

    }

    public void insert(AssessmentDataStruct assessment) {
        repository.insert(assessment);
    }
    public void delete(AssessmentDataStruct assessment) {
        repository.delete(assessment);
    }
    public void update(AssessmentDataStruct assessment) {
        repository.update(assessment);
    }

    public LiveData<List<AssessmentDataStruct>> getAllAssessments() {
        return allAssessments;
    }
}
