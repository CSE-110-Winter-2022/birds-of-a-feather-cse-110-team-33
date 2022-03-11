package com.example.birds_of_a_feather_cse_110_team_33;

import static android.content.Context.MODE_PRIVATE;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.CoursesDao;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class LoadSessionTests {
    private PersonDao personDao;
    private CoursesDao coursesDao;
    private AppDatabase db;

    @Before
    public void setupTestDb() {
        Context context = getApplicationContext();
        AppDatabase.useTestSingleton(context);
        db = AppDatabase.singleton(context);
        personDao = db.personDao();
        coursesDao = db.coursesDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testLoadSessionName() {


        //Fill the database with sample data
        Person james = new Person("James", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        james.setPersonId(personDao.maxId() + 1);
        personDao.insert(james);
        Course james110 = new Course(james.getPersonId(), 2021, "Winter", "CSE", "110","Tiny");
        Course james112 = new Course(james.getPersonId(), 2021, "Winter", "CSE", "112","Small");
        Course james132A = new Course(james.getPersonId(), 2021, "Spring", "CSE", "132A","Large");
        coursesDao.insert(james110);
        coursesDao.insert(james112);
        coursesDao.insert(james132A);

        // share none
        Person nick = new Person("Nick", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        nick.setPersonId(personDao.maxId() + 1);
        personDao.insert(nick);
        Course nick110 = new Course(nick.getPersonId(), 2022, "Winter", "CSE", "110","Tiny");
        Course nick112 = new Course(nick.getPersonId(), 2022, "Winter", "CSE", "112","Small");
        Course nick132A = new Course(nick.getPersonId(), 2022, "Spring", "CSE", "132A","Large");
        coursesDao.insert(nick110);
        coursesDao.insert(nick112);
        coursesDao.insert(nick132A);

        // share one
        Person ryan = new Person("Ryan", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        ryan.setPersonId(personDao.maxId() + 1);
        personDao.insert(ryan);
        Course ryan110 = new Course(ryan.getPersonId(), 2021, "Winter", "CSE", "110","Tiny");
        Course ryan112 = new Course(ryan.getPersonId(), 2022, "Winter", "CSE", "112","Small");
        Course ryan132A = new Course(ryan.getPersonId(), 2022, "Spring", "CSE", "132A","Large");
        coursesDao.insert(ryan110);
        coursesDao.insert(ryan112);
        coursesDao.insert(ryan132A);

        HomePageActivity activityHP = Robolectric.setupActivity(HomePageActivity.class);
        Context contextHP = activityHP;

        Button save = activityHP.findViewById(R.id.save_session_btn);

        //Save the current DB info
        save.performClick();

        //Test Something
        SaveSessionActivity activity = Robolectric.setupActivity(SaveSessionActivity.class);

        Context context = activity;//ApplicationProvider.getApplicationContext();

        EditText sessionName = activity.findViewById(R.id.save_session_title);

        sessionName.setText("Session1");

        activity.findViewById(R.id.save_session_name_btn).performClick();

        LoadSessionActivity activity2 = Robolectric.setupActivity(LoadSessionActivity.class);

        Context context2 = activity2;//ApplicationProvider.getApplicationContext();

        activity2.findViewById(R.id.session_list_spinner);



        SharedPreferences preferences = activity2.getSharedPreferences("pref one",MODE_PRIVATE);


        String session = preferences.getString("sessionTitle"+1,"default" + 1);

        Button LoadChoice = activity2.findViewById(R.id.confirm_load_session_btn);

        LoadChoice.performClick();

        List<Person> tempList = db.personDao().getAll();

        Person joe = new Person("Joe", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        joe.setPersonId(personDao.maxId() + 1);
        personDao.insert(joe);
        Course joe110 = new Course(joe.getPersonId(), 2021, "Winter", "CSE", "110","Tiny");
        Course joe112 = new Course(joe.getPersonId(), 2022, "Winter", "CSE", "112","Small");
        Course joe132A = new Course(joe.getPersonId(), 2022, "Spring", "CSE", "132A","Large");
        coursesDao.insert(joe110);
        coursesDao.insert(joe112);
        coursesDao.insert(joe132A);

        HomePageActivity activityHP2 = Robolectric.setupActivity(HomePageActivity.class);
        Context contextHP2 = activityHP2;


        Gson gson = new Gson();
        String json = preferences.getString("savedPersons" + 1, "");

        //Necessary for lists
        Type personListType = new TypeToken<List<Person>>() {}.getType();

        List<Person> personsToLoad = gson.fromJson(json, personListType);

        boolean same = true;

        for (int i = 0; i < tempList.size(); i++) {
            if (!tempList.get(i).name.equals(personsToLoad.get(i).name)) {
                same = false;
            }
        }


        assertEquals("Session1",session);
        assertEquals(3,tempList.size());
        assertTrue(same);





    }


}
