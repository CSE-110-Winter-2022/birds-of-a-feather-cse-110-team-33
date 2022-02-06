package com.example.birds_of_a_feather_cse_110_team_33.model.db;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

// data accessing object for personWithCourses
@Dao
public interface PersonWithCoursesDao {
    @Transaction
    @Query("SELECT * FROM persons")
    List<PersonWithCourses> getAll();

    @Query("SELECT * FROM persons WHERE id=:id")
    PersonWithCourses get(int id);

    @Query("SELECT COUNT(*) FROM persons")
    int count();
}