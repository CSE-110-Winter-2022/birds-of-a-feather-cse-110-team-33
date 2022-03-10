package com.example.birds_of_a_feather_cse_110_team_33.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface CoursesDao {
    @Transaction
    @Query("SELECT * FROM courses WHERE person_id=:personId")
    List<Course> getForPerson(int personId);

    // change person_id to course_id?
    @Query("SELECT * FROM courses WHERE person_id=:id")
    Course get(int id);

    @Insert
    void insert(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT count(*) FROM courses")
    int count();

    @Query("SELECT count(*) FROM courses WHERE person_id=:personId")
    int countPerson(int personId);


}