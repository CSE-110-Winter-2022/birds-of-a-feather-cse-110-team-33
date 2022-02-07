package com.example.birds_of_a_feather_cse_110_team_33.model;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;

import java.util.List;

// interface for a user
public interface IPerson {
    int getId();
    String getName();
    List<Course> getCourses();
    String getPhoto();
    List<Course> getSharedCourses(IPerson classMate);
}
