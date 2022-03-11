package com.example.birds_of_a_feather_cse_110_team_33;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.*;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class FavoritesTest {
    private PersonDao personDao;
    private AppDatabase db;
    private Person ethan;
    private Person james;
    private Person nick;

    @Before
    public void setupTestDb() {
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        db = AppDatabase.singleton(context);
        personDao = db.personDao();


        ethan = new Person("Ethan", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        ethan.setPersonId(0);
        ethan.setNFavorite();
        personDao.insert(ethan);
        Course ethan110 = new Course(ethan.getPersonId(), 2022, "Winter", "CSE", "110", "Tiny");
        Course ethan112 = new Course(ethan.getPersonId(), 2022, "Winter", "CSE", "112", "Small");
        Course ethan132A = new Course(ethan.getPersonId(), 2022, "Spring", "CSE", "132A", "Large");
        db.coursesDao().insert(ethan110);
        db.coursesDao().insert(ethan112);
        db.coursesDao().insert(ethan132A);

        james = new Person("James", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        james.setPersonId(1);
        james.setFavorite();
        personDao.insert(james);
        Course james110 = new Course(james.getPersonId(), 2022, "Winter", "CSE", "110","Tiny");
        Course james112 = new Course(james.getPersonId(), 2022, "Winter", "CSE", "112","Small");
        Course james132A = new Course(james.getPersonId(), 2022, "Spring", "CSE", "132A","Large");
        db.coursesDao().insert(james110);
        db.coursesDao().insert(james112);
        db.coursesDao().insert(james132A);

        nick = new Person("Nick", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        nick.setPersonId(personDao.maxId() + 1);
        nick.setFavorite();
        personDao.insert(nick);
        Course nick110 = new Course(nick.getPersonId(), 2021, "Winter", "CSE", "110","Tiny");
        Course nick112 = new Course(nick.getPersonId(), 2021, "Winter", "CSE", "112","Small");
        Course nick132A = new Course(nick.getPersonId(), 2021, "Spring", "CSE", "132A","Large");
        db.coursesDao().insert(nick110);
        db.coursesDao().insert(nick112);
        db.coursesDao().insert(nick132A);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testSavingFavorites() {
        PersonDetailActivity activity = Robolectric.setupActivity(PersonDetailActivity.class);
        activity.findViewById(R.id.favorite).performClick();

        assertEquals(true, personDao.get(0).isFavorite);
    }

    @Test
    public void testFavoritesList() {
        FavoriteListActivity activity = Robolectric.setupActivity(FavoriteListActivity.class);
        List<Person> persons = db.personDao().getAll();

        // remove unfavorited person
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).isFavorite == false) {
                persons.remove(persons.get(i));
                i--;
            }
        }
        activity.setPersonNumShared(persons, ethan);
        assertEquals(2, persons.size());
    }
}
