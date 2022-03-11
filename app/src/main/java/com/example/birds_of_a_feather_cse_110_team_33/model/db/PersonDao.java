package com.example.birds_of_a_feather_cse_110_team_33.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

// data accessing object for person
@Dao
public interface PersonDao {
    @Query("SELECT * FROM persons")
    List<Person> getAll();

    @Query("SELECT * FROM persons WHERE person_id=:id")
    Person get(int id);

    @Query("SELECT COUNT(*) FROM persons")
    int count();

    @Query("SELECT MAX(person_id) FROM persons")
    int maxId();

    @Query("SELECT b.* FROM courses a, courses b " +
            "WHERE a.person_id=:id AND b.person_id=:userId " + //compares user and other
            "AND a.quarter=b.quarter " +
            "AND a.subject=b.subject " +
            "AND a.year=b.year " +
            "AND a.course_num=b.course_num")
    List<Course> getSharedCourses(int id, int userId);

    @Query("SELECT b.* FROM courses a, courses b " +
            "WHERE a.person_id=:id AND b.person_id=:userId " + //compares user and other
            "AND a.quarter=b.quarter " +
            "AND a.subject=b.subject " +
            "AND a.year=b.year " +
            "AND a.course_num=b.course_num " +
            "AND a.quarter=:current_qtr " +
            "AND a.year=:current_year")
    List<Course> getCurrentSharedCourses(int id, int userId, String current_qtr, int current_year);

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);

    @Update
    void update(Person person);
}