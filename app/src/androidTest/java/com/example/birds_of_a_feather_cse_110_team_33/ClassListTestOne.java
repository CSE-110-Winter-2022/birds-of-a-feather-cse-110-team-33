package com.example.birds_of_a_feather_cse_110_team_33;
import static androidx.test.espresso.action.ViewActions.click;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.AddAClassActivity;
import com.example.birds_of_a_feather_cse_110_team_33.UserClassListActivity;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.CoursesDao;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class ClassListTestOne {
    private PersonDao personDao;
    private CoursesDao coursesDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        db = AppDatabase.singleton(context);
        personDao = db.personDao();
        coursesDao = db.coursesDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }



    /*
    @Test
    public void testCourseAdded() throws Exception {

        Person ethan = new Person("Ethan", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        ethan.setPersonId(personDao.maxId() + 1);
        personDao.insert(ethan);

        Button saveBtn =  (Button) getView().findViewById(R.id.save_class_button);
        onViewWithId(R.id.save_class_button).perform(click());

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.classrow, null);

    }

     */





}
