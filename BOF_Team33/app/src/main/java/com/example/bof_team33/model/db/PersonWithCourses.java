package com.example.bof_team33.model.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.bof_team33.model.IPerson;

import java.util.List;

// db for person with courses
public class PersonWithCourses implements IPerson {
    @Embedded
    public Person person;

    // note: no projection bc we want entire course, not just the text like in lab 5
    // makes accessing each individual field easier later on
    @Relation(parentColumn = "id",
            entityColumn = "person_id",
            entity = Course.class)
    public List<Course> courses;

    @Override
    public String getName() { return this.person.name; }

    // gonna have to implement algorithm elsewhere
    @Override
    public List<Course> getCourses() { return this.courses; }

    @Override
    public int getId() { return this.person.personId; }

    @Override
    public String getPhoto() { return this.person.photo; }
}
