package com.example.birds_of_a_feather_cse_110_team_33.filtering_sorting;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.List;

public class CurrentFilter implements IFilter {
    private AppDatabase db;
    private List<Course> sharedCourses;
    /*
    public void setPersonNumShared(List<Person> persons, Person user){
        for(Person person: persons){
            person.setNumShared(db.personDao().
                    getCurrentSharedCourses(person.getPersonId(), user.getPersonId()).size());
        }
    }*/

    @Override
    public void filter(List<Person> persons) {

    }
}
