package com.example.guru.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssessmentDao {
    @Insert
    void insert(AssessmentDataStruct assessment);

    @Update
    void update(AssessmentDataStruct assessment);

    @Delete
    void delete(AssessmentDataStruct assessment);

    @Query("SELECT * FROM assessment_table ORDER BY dateCreated DESC")
    LiveData<List<AssessmentDataStruct>> getAllAssessments();

}
