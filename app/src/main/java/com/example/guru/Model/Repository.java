package com.example.guru.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private AssessmentDao assessmentDao;
    private LiveData<List<AssessmentDataStruct>> assessmentList;

    public Repository(Application application) {
        AssessmentDataBase dataBase = AssessmentDataBase.getInstance(application);
        assessmentDao = dataBase.assessmentDao();
        assessmentList = assessmentDao.getAllAssessments();
    }

    public void insert(AssessmentDataStruct assessment) {
        new InsertAssessmentAsyncTask(assessmentDao).execute(assessment);
    }

    public void update(AssessmentDataStruct assessment) {
        new UpdateAssessmentAsyncTask(assessmentDao).execute(assessment);
    }

    public void delete(AssessmentDataStruct assessment) {
        new DeleteAssessmentAsyncTask(assessmentDao).execute(assessment);
    }

    public LiveData<List<AssessmentDataStruct>> getAssessmentList() {
        return assessmentList;
    }

    private static class InsertAssessmentAsyncTask extends AsyncTask<AssessmentDataStruct,Void,Void> {
        private AssessmentDao assessmentDao;

        private InsertAssessmentAsyncTask(AssessmentDao assessmentDao) {
            this.assessmentDao = assessmentDao;
        }

        @Override
        protected Void doInBackground(AssessmentDataStruct... assessmentDataStructs) {
            assessmentDao.insert(assessmentDataStructs[0]);
            return null;
        }
    }
    private static class UpdateAssessmentAsyncTask extends AsyncTask<AssessmentDataStruct,Void,Void> {
        private AssessmentDao assessmentDao;

        private UpdateAssessmentAsyncTask(AssessmentDao assessmentDao) {
            this.assessmentDao = assessmentDao;
        }

        @Override
        protected Void doInBackground(AssessmentDataStruct... assessmentDataStructs) {
            assessmentDao.update(assessmentDataStructs[0]);
            return null;
        }
    }
    private static class DeleteAssessmentAsyncTask extends AsyncTask<AssessmentDataStruct,Void,Void> {
        private AssessmentDao assessmentDao;

        private DeleteAssessmentAsyncTask(AssessmentDao assessmentDao) {
            this.assessmentDao = assessmentDao;
        }

        @Override
        protected Void doInBackground(AssessmentDataStruct... assessmentDataStructs) {
            assessmentDao.delete(assessmentDataStructs[0]);
            return null;
        }
    }
}
