package com.example.birds_of_a_feather_cse_110_team_33.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

// data accessing object for personWithCourses
@Dao
public interface PersonDao {
    @Query("SELECT * FROM persons")
    List<Person> getAll();

    @Query("SELECT * FROM persons WHERE person_id=:id")
    Person get(int id);

    @Query("SELECT COUNT(*) FROM persons")
    int count();

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);
}