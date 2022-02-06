package com.example.bof_team33.model;

import com.example.bof_team33.model.db.Course;

import java.util.Arrays;
import java.util.List;

// use this class for base testing
// if the database isn't working -_-
public class DummyPerson  implements IPerson {
    private final int id;
    private final String name;
    private final List<Course> courses;
    private final String photo;

    public DummyPerson(int id, String name, Course[] courses, String photo) {
        this.id = id;
        this.name = name;
        this.courses = Arrays.asList(courses);
        this.photo = photo;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public List<Course> getCourses() { return courses; }

    @Override
    public String getPhoto() { return photo; }
}
