package com.example.birds_of_a_feather_cse_110_team_33.filtering;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CurrentFilter implements IFilter {
    private AppDatabase db;
    private List<Course> sharedCourses;

    @Override
    public void filter(List<Person> persons) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getCurrentShared() < 2) {
                persons.remove(i);
                i--;
            }
        }

        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                if(p1.waved() && !p2.waved()) {
                    return 1;
                } else if(p2.waved() && !p1.waved()) {
                    return -1;
                }
                return p2.getCurrentShared() - p1.getCurrentShared();
            }
        });
    }
}
