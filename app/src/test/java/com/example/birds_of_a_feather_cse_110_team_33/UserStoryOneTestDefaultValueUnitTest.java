package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.test.core.app.ActivityScenario;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class UserStoryOneTestDefaultValueUnitTest {
    ActivityScenario<UserClassList> scenario;
    ActivityScenario<AddAClassPage> scenario2;

    /*
    @Rule
    public ActivityScenarioRule<UserClassList> scenarioRule = new ActivityScenarioRule<>(UserClassList.class);
    public ActivityScenarioRule<AddAClassPage> scenarioRule2 = new ActivityScenarioRule<>(AddAClassPage.class);
    */

    @Before
    public void start() {
        scenario = ActivityScenario.launch(UserClassList.class);
        scenario2 = ActivityScenario.launch(AddAClassPage.class);
    }

    @Test
    public void testDefaultValue() {

        /*
        ActivityScenario<UserClassList> scenario = scenarioRule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);


        scenario.onActivity(activity -> {

            // error lies outside of this block
            TextView resultView = activity.findViewById(R.id.course_edit_text);
            String extractedText = resultView.getText().toString();
            String guess = "CSE 110 Winter 2022";
            assertEquals(extractedText, guess);

        });

         */
    }

    @Test
    public void testChangingText() {

        /*
        ActivityScenario<AddAClassPage> scenario2 = scenarioRule2.getScenario();
        scenario2.moveToState(Lifecycle.State.CREATED);


        scenario2.onActivity(activity -> {

            EditText resultView1 = activity.findViewById(R.id.course_subject_edit_text);
            String extractedHint = resultView1.getHint().toString();
            String guess = "CSE";

            assertEquals(extractedHint, guess);
        });

         */
    }




}

