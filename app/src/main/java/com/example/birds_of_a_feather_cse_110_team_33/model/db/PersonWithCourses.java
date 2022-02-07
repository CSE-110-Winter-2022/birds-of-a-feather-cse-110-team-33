package com.example.birds_of_a_feather_cse_110_team_33.model.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.birds_of_a_feather_cse_110_team_33.model.IPerson;

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

    @Override
    public List<Course> getCourses() { return this.courses; }

    @Override
    public int getId() { return this.person.personId; }

    @Override
    public String getPhoto() { return this.person.photo; }

    // IMPLEMENT HERE TO GET SHARED COURSES!!!!
    @Override
    public List<Course> getSharedCourses(IPerson classMate) { return this.courses; }
}
