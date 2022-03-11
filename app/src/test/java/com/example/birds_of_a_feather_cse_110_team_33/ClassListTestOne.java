package com.example.birds_of_a_feather_cse_110_team_33;

import static org.junit.Assert.assertEquals;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.*;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class ClassListTestOne {
    private PersonDao personDao;
    private CoursesDao coursesDao;
    private AppDatabase db;
    public final String[] years = new String[]{"2016","2017","2018","2019","2020","2021","2022"};
    public final String[] quarters = new String[]{"Winter","Spring","Summer_1","Summer_2","SSS","Fall"};


    @Before
    public void setupTestDb() {
        Context context = ApplicationProvider.getApplicationContext();
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
    public void testSavingCourse() {
        AddAClassActivity activity = Robolectric.setupActivity(AddAClassActivity.class);

        TextView courseName = (TextView) activity.findViewById(R.id.course_subject_edit_text);
        TextView courseNum = (TextView) activity.findViewById(R.id.course_number_edit_text);
        Spinner season = (Spinner) activity.findViewById(R.id.quarters_spinner);
        Spinner year = (Spinner) activity.findViewById(R.id.years_spinner);


        courseName.setText("CSE");
        courseNum.setText("110");
        season.setSelection(0);
        year.setSelection(6);

        Person user = new Person("Connor","https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        user.setPersonId(0);
        personDao.insert(user);

        activity.findViewById(R.id.save_class_button).performClick();



        int userCourseCount = coursesDao.count();


        assertEquals(1, userCourseCount);

    }
    @Test
    public void testCourseStringSaved() {
        AddAClassActivity activity = Robolectric.setupActivity(AddAClassActivity.class);

        TextView courseName = (TextView) activity.findViewById(R.id.course_subject_edit_text);
        TextView courseNum = (TextView) activity.findViewById(R.id.course_number_edit_text);
        Spinner season = (Spinner) activity.findViewById(R.id.quarters_spinner);
        Spinner year = (Spinner) activity.findViewById(R.id.years_spinner);


        //Add a course
        courseName.setText("CSE");
        courseNum.setText("110");
        season.setSelection(0);
        year.setSelection(6);


        activity.findViewById(R.id.save_class_button).performClick();

        //Add a second course
        courseName.setText("CSE");
        courseNum.setText("167");
        season.setSelection(0);
        year.setSelection(5);


        activity.findViewById(R.id.save_class_button).performClick();

        //Test a faulty course name, it shouldn't be added to the db
        courseName.setText("100");
        courseNum.setText("167");
        season.setSelection(0);
        year.setSelection(5);


        activity.findViewById(R.id.save_class_button).performClick();

        //Test an empty course name
        courseName.setText("");
        courseNum.setText("167");
        season.setSelection(0);
        year.setSelection(5);


        activity.findViewById(R.id.save_class_button).performClick();

        //Test a course name that is too long
        courseName.setText("CSECSE");
        courseNum.setText("167");
        season.setSelection(0);
        year.setSelection(5);


        activity.findViewById(R.id.save_class_button).performClick();




        Person user = new Person("Connor","https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        user.setPersonId(0);
        personDao.insert(user);

        String courseString = "CSE 110 Winter 2022 Tiny";
        String courseString3 = "CSE 167 Winter 2021 Tiny";
        List<Course> courses = coursesDao.getForPerson(0);
        String courseString2 = courses.get(0).toString();
        String courseString4 = courses.get(1).toString();

        assertEquals(courseString, courseString2);
        assertEquals(courseString3, courseString4);
        assertEquals(2,courses.size());


    }

    @Test
    public void testEmptyClassList() {
        ActivityScenario<UserClassListActivity> scenario = ActivityScenario.launch(UserClassListActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);

        // No class added --> empty
        scenario.onActivity(activity -> {
            TextView myName = activity.findViewById(R.id.course_edit_text);
            assertEquals("Example",myName.getText().toString());


        });

        scenario.moveToState(Lifecycle.State.DESTROYED);

    }





}
