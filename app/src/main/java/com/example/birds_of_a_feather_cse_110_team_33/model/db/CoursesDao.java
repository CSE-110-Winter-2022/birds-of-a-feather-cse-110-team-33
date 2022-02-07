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
    @Query("SELECT * FROM course where person_id =:personId")
    List<Course> getForPerson(int personId);

    @Query("SELECT * FROM course WHERE id=:id")
    Course get(int id);

    @Query("SELECT COUNT(*) from course")
    int count();
    @Insert
    void insert(Course note);

    @Delete
    void delete(Course note);


}
