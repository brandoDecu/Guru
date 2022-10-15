package com.example.guru.Model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;


@Database(entities = {AssessmentDataStruct.class}, version = 1, exportSchema = false)
public abstract class AssessmentDataBase extends RoomDatabase {
    private static AssessmentDataBase instance;
    public abstract AssessmentDao assessmentDao();

    public static synchronized AssessmentDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AssessmentDataBase.class, "assessment_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();
        }
        return instance;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private AssessmentDao assessmentDao;

        private PopulateDbAsyncTask(AssessmentDataBase db) {
            assessmentDao = db.assessmentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AssessmentDataStruct newAssessment = new AssessmentDataStruct();
            newAssessment.setJavaDateCreated(new Date());
            AssessmentDataStruct newAssessment2 = new AssessmentDataStruct();
            newAssessment2.setJavaDateCreated(new Date());
            newAssessment2.setLongestText("whassuuuuuuuuup");

            assessmentDao.insert(newAssessment);
            assessmentDao.insert(newAssessment2);
            return null;
        }
    }

}
