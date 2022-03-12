package com.example.birds_of_a_feather_cse_110_team_33.filtering;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// filters based on total number of shared classes, is the default when they first start searching
public class TotalFilter implements IFilter {

    // takes in a list of persons and sorts them based on total num of shared courses
    @Override
    public void filter(List<Person> persons) {
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                if(p1.waved() && !p2.waved()) {
                    return 1;
                } else if(p2.waved() && !p1.waved()) {
                    return -1;
                }
                return p2.getNumShared() - p1.getNumShared();
            }
        });
    }
}
