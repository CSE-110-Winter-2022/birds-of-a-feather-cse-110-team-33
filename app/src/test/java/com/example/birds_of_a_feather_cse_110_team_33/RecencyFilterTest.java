package com.example.birds_of_a_feather_cse_110_team_33;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.example.birds_of_a_feather_cse_110_team_33.filtering.*;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.CoursesDao;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.*;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class RecencyFilterTest {
    private PersonDao personDao;
    private AppDatabase db;
    private Person james;
    private Person nick;
    private Person ryan;
    private Person ethan;
    private CoursesDao coursesDao;


    @Before
    public void setupTestDb() {
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        db = AppDatabase.singleton(context);
        personDao = db.personDao();
        coursesDao = db.coursesDao();

        // PREPOPULATE DATABASE

        ethan = new Person("Ethan", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        ethan.setPersonId(personDao.maxId() + 1);
        personDao.insert(ethan);
        Course ethan110 = new Course(ethan.getPersonId(), 2022, "Winter", "CSE", "110", "Tiny");
        Course ethan112 = new Course(ethan.getPersonId(), 2022, "Winter", "CSE", "112", "Small");
        Course ethan132A = new Course(ethan.getPersonId(), 2022, "Spring", "CSE", "132A", "Large");
        Course ethanNew1 = new Course(ethan.getPersonId(), 2021, "Winter", "CSE", "110", "Tiny");
        Course ethanNew2 = new Course(ethan.getPersonId(), 2021, "Winter", "CSE", "112", "Small");
        Course ethanNew3 = new Course(ethan.getPersonId(), 2021, "Spring", "CSE", "132A", "Large");
        coursesDao.insert(ethan110);
        coursesDao.insert(ethan112);
        coursesDao.insert(ethan132A);
        coursesDao.insert(ethanNew1);
        coursesDao.insert(ethanNew2);
        coursesDao.insert(ethanNew3);

        // share all
        james = new Person("James", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        james.setPersonId(personDao.maxId() + 1);
        personDao.insert(james);
        Course james110 = new Course(james.getPersonId(), 2022, "Winter", "CSE", "110","Tiny");
        Course james112 = new Course(james.getPersonId(), 2022, "Winter", "CSE", "112","Small");
        Course james132A = new Course(james.getPersonId(), 2022, "Spring", "CSE", "132A","Large");
        coursesDao.insert(james110);
        coursesDao.insert(james112);
        coursesDao.insert(james132A);

        // share none
        nick = new Person("Nick", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        nick.setPersonId(personDao.maxId() + 1);
        personDao.insert(nick);
        Course nick110 = new Course(nick.getPersonId(), 2021, "Winter", "CSE", "110","Tiny");
        Course nick112 = new Course(nick.getPersonId(), 2021, "Winter", "CSE", "112","Small");
        Course nick132A = new Course(nick.getPersonId(), 2021, "Spring", "CSE", "132A","Large");
        coursesDao.insert(nick110);
        coursesDao.insert(nick112);
        coursesDao.insert(nick132A);

        // share two
        ryan = new Person("Ryan", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        ryan.setPersonId(personDao.maxId() + 1);
        personDao.insert(ryan);
        Course ryan110 = new Course(ryan.getPersonId(), 2022, "Winter", "CSE", "110","Tiny");
        Course ryan112 = new Course(ryan.getPersonId(), 2021, "Winter", "CSE", "112","Small");
        Course ryan132A = new Course(ryan.getPersonId(), 2022, "Spring", "CSE", "132A","Large");
        coursesDao.insert(ryan110);
        coursesDao.insert(ryan112);
        coursesDao.insert(ryan132A);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testRecencyFilter() {
        HomePageActivity activity = Robolectric.setupActivity(HomePageActivity.class);
        IFilter filter = new RecencyFilter(activity.getApplicationContext(), ethan.getPersonId());
        List<Person> persons = db.personDao().getAll();

        // remove user
        for (Person person: persons) {
            if (person.getPersonId() == ethan.getPersonId()) {
                persons.remove(person);
                break;
            }
        }

        filter.filter(persons);

        for (int i = 0; i < persons.size(); i++) {
            Person curr = persons.get(i);
            if (i == 0) {
                assertTrue(curr.getName().equals("Nick"));
                assertEquals(7.0, curr.getRecencyValue(), 0.1);
            }
            else if (i == 1) {
                assertTrue(curr.getName().equals("Ryan"));
                assertEquals(2.0, curr.getRecencyValue(), 0.1);
            }
            else if (i == 2) {
                assertTrue(curr.getName().equals("James"));
                assertEquals(0.0, curr.getRecencyValue(), 0.1);
            }
        }
    }
}
