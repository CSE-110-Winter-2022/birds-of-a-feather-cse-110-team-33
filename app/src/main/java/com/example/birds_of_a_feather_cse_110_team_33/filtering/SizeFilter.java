package com.example.birds_of_a_feather_cse_110_team_33.filtering;

import android.content.Context;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SizeFilter implements IFilter {
    private AppDatabase db;
    private PersonDao personDao;
    private int userId;

    public SizeFilter(Context context, int userId) {
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
                String courseSize = course.getCourseSize();
                if (courseSize.equals("Tiny")) {
                    curr.setSizeValue(curr.getSizeValue() + 1);
                }
                else if (courseSize.equals("Small")) {
                    curr.setSizeValue(curr.getSizeValue() + .33);
                }
                else if (courseSize.equals("Medium")) {
                    curr.setSizeValue(curr.getSizeValue() + .18);
                }
                else if (courseSize.equals("Large")) {
                    curr.setSizeValue(curr.getSizeValue() + .10);
                }
                else if (courseSize.equals("Huge")) {
                    curr.setSizeValue(curr.getSizeValue() + .06);
                }
                else if (courseSize.equals("Gigantic")) {
                    curr.setSizeValue(curr.getSizeValue() + .03);
                }
            }
        }

        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Double.compare(p2.getSizeValue(), p1.getSizeValue());
            }
        });
    }
}

