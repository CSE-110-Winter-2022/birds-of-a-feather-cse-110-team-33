package com.example.birds_of_a_feather_cse_110_team_33;

import static org.junit.Assert.assertEquals;

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
public class FavoritesTest {
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
    public void testSavingFavorites() {
        PersonDetailActivity activity = Robolectric.setupActivity(PersonDetailActivity.class);

        activity.findViewById(R.id.favorite).performClick();

        assertEquals(true, personDao.get(1).isFavorite);
    }

    @Test
    public void testFavoritesList() {

    }
}
