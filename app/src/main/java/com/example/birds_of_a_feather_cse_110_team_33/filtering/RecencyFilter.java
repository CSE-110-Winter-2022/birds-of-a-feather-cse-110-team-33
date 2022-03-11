package com.example.birds_of_a_feather_cse_110_team_33.filtering;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecencyFilter implements IFilter {
    private AppDatabase db;
    private PersonDao personDao;
    private int userId;

    public RecencyFilter(Context context, int userId) {
        db = AppDatabase.singleton(context);
        personDao = db.personDao();
        this.userId = userId;
    }

    @Override
    public void filter(List<Person> persons) {
        for (int i = 0; i < persons.size(); i++) {
            Person curr = persons.get(i);
            List<Course> sharedCourses =  personDao.getSharedCourses(curr.getPersonId(), userId);
            for (Course course: sharedCourses) {
                String qtr = course.getQtr();
                int year = course.getYear();

                if (year == 2022) {
                    continue;
                }
                else if (year == 2021) {
                    if (qtr.equals("Fall")) {
                        // age == 0
                        curr.setRecencyValue(curr.getRecencyValue() + 5);
                    }
                    else if (qtr.equals("SSS") || qtr.equals("Summer 1") || qtr.equals("Summer 2")) {
                        // age == 1
                        curr.setRecencyValue(curr.getRecencyValue() + 4);
                    }
                    else if (qtr.equals("Spring")) {
                        // age == 2
                        curr.setRecencyValue(curr.getRecencyValue() + 3);
                    }
                    else if (qtr.equals("Winter")) {
                        // age == 3
                        curr.setRecencyValue(curr.getRecencyValue() + 2);
                    }
                }
                else {
                    curr.setRecencyValue(curr.getRecencyValue() + 1);
                }
            }
        }

        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Double.compare(p2.getRecencyValue(), p1.getRecencyValue());
            }
        });
    }
}
