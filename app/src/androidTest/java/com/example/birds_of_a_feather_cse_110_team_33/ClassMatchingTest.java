package com.example.birds_of_a_feather_cse_110_team_33;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.CoursesDao;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ClassMatchingTest {
    private PersonDao personDao;
    private CoursesDao coursesDao;
    private AppDatabase db;
    private Person james;
    private Person nick;
    private Person ryan;


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        db = AppDatabase.singleton(context);
        personDao = db.personDao();
        coursesDao = db.coursesDao();

        // PREPOPULATE DATABASE

        // share all
        james = new Person("James", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        james.setPersonId(personDao.maxId() + 1);
        personDao.insert(james);
        Course james110 = new Course(james.getPersonId(), 2021, "Winter", "CSE", "110");
        Course james112 = new Course(james.getPersonId(), 2021, "Winter", "CSE", "112");
        Course james132A = new Course(james.getPersonId(), 2021, "Spring", "CSE", "132A");
        coursesDao.insert(james110);
        coursesDao.insert(james112);
        coursesDao.insert(james132A);

        // share none
        nick = new Person("Nick", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        nick.setPersonId(personDao.maxId() + 1);
        personDao.insert(nick);
        Course nick110 = new Course(nick.getPersonId(), 2022, "Winter", "CSE", "110");
        Course nick112 = new Course(nick.getPersonId(), 2022, "Winter", "CSE", "112");
        Course nick132A = new Course(nick.getPersonId(), 2022, "Spring", "CSE", "132A");
        coursesDao.insert(nick110);
        coursesDao.insert(nick112);
        coursesDao.insert(nick132A);

        // share one
        ryan = new Person("Ryan", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        ryan.setPersonId(personDao.maxId() + 1);
        personDao.insert(ryan);
        Course ryan110 = new Course(ryan.getPersonId(), 2021, "Winter", "CSE", "110");
        Course ryan112 = new Course(ryan.getPersonId(), 2022, "Winter", "CSE", "112");
        Course ryan132A = new Course(ryan.getPersonId(), 2022, "Spring", "CSE", "132A");
        coursesDao.insert(ryan110);
        coursesDao.insert(ryan112);
        coursesDao.insert(ryan132A);
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testMatching() throws Exception{
        // created new user
        Person ethan = new Person("Ethan", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        ethan.setPersonId(personDao.maxId() + 1);
        personDao.insert(ethan);
        Course ethan110 = new Course(ethan.getPersonId(), 2021, "Winter", "CSE", "110");
        Course ethan112 = new Course(ethan.getPersonId(), 2021, "Winter", "CSE", "112");
        Course ethan132A = new Course(ethan.getPersonId(), 2021, "Spring", "CSE", "132A");
        coursesDao.insert(ethan110);
        coursesDao.insert(ethan112);
        coursesDao.insert(ethan132A);

        assertEquals(4, ethan.getPersonId());

        // get all people
        List<Person> persons = db.personDao().getAll();

        assertEquals(persons.size(), 4);

        // remove user
        for (Person person: persons) {
            if (person.getPersonId() == ethan.getPersonId()) {
                persons.remove(person);
                break;
            }
        }

        assertEquals(ethan.getNumShared(), 0);
        assertEquals(persons.size(), 3);

        // set shared courses variable
        for(Person person: persons){
            List<Course> shared = personDao.getSharedCourses(person.getPersonId(), ethan.getPersonId());
            person.setNumShared(shared.size());
            person.getNumShared();
        }

        assertEquals(3, personDao.get(1).getNumShared());
        //assertEquals(0, nick.getNumShared());
        //assertEquals(1, ryan.getNumShared());
    }
}