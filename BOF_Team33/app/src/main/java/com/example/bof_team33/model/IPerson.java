package com.example.bof_team33.model;

import com.example.bof_team33.model.db.Course;

import java.util.List;

// interface for a user
public interface IPerson {
    int getId();
    String getName();
    List<Course> getCourses();
    String getPhoto();
    // shared courses method?
}
