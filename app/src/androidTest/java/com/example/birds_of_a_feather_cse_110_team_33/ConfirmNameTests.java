package com.example.birds_of_a_feather_cse_110_team_33;

import static org.junit.Assert.assertEquals;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.*;

@RunWith(RobolectricTestRunner.class)
public class ConfirmNameTests {
    private PersonDao personDao;
    private AppDatabase db;


    @Before
    public void setupTestDb() {
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        db = AppDatabase.singleton(context);
        personDao = db.personDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testSavingName() {
        ConfirmNameActivity activity = Robolectric.setupActivity(ConfirmNameActivity.class);

        activity.findViewById(R.id.confirm_name).performClick();

        assertEquals("Ethan", personDao.get(1).name);
    }

    @Test
    public void testEditingAndSavingName() {
        ConfirmNameActivity activity = Robolectric.setupActivity(ConfirmNameActivity.class);

        ((TextView)activity.findViewById(R.id.user_name)).setText("Ethan2");
        activity.findViewById(R.id.confirm_name).performClick();

        assertEquals("Ethan2", personDao.get(1).name);
    }
}