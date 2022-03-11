package com.example.birds_of_a_feather_cse_110_team_33;

import static android.content.Context.MODE_PRIVATE;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.*;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class SaveSessionTests {
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
    public void testSessionNameEmpty() {



        SaveSessionActivity activity = Robolectric.setupActivity(SaveSessionActivity.class);

        Context context = activity;//ApplicationProvider.getApplicationContext();


        EditText sessionName = activity.findViewById(R.id.save_session_title);
        
        sessionName.setText("");

        activity.findViewById(R.id.save_session_name_btn).performClick();


        SharedPreferences preferences = context.getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String storedString = preferences.getString("sessionTitle"+0,"");

        assertNotEquals("",storedString);
      
        
    }

    @Test
    public void testSessionNameNonEmpty() {



        SaveSessionActivity activity = Robolectric.setupActivity(SaveSessionActivity.class);

        Context context = getApplicationContext();

        EditText sessionName = (EditText) activity.findViewById(R.id.save_session_title);


        sessionName.setText("Session1");


        activity.findViewById(R.id.save_session_name_btn).performClick();


        SharedPreferences preferences = context.getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String storedString = preferences.getString("sessionTitle"+0,"default");

        assertEquals("Session1",storedString);


    }






}