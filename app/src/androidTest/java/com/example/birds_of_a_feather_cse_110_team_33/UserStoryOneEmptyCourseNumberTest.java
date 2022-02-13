package com.example.birds_of_a_feather_cse_110_team_33;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UserStoryOneEmptyCourseNumberTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void userStoryOneEmptyCourseNumberTest() {
        ViewInteraction materialButton = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.class_list_btn), ViewMatchers.withText("Class List"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        materialButton.perform(ViewActions.click());

        ViewInteraction materialButton2 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.add_field_button_LD), ViewMatchers.withText("Add Class"),
                        childAtPosition(
                                Matchers.allOf(ViewMatchers.withId(R.id.parent_linear_layout),
                                        childAtPosition(
                                                ViewMatchers.withId(android.R.id.content),
                                                0)),
                                1),
                        ViewMatchers.isDisplayed()));
        materialButton2.perform(ViewActions.click());

        ViewInteraction appCompatEditText = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.course_subject_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        appCompatEditText.perform(ViewActions.replaceText("CSE"), ViewActions.closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.course_number_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                3),
                        ViewMatchers.isDisplayed()));
        appCompatEditText2.perform(ViewActions.replaceText("167"), ViewActions.closeSoftKeyboard());

        ViewInteraction appCompatSpinner = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.years_spinner),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                7),
                        ViewMatchers.isDisplayed()));
        appCompatSpinner.perform(ViewActions.click());

        DataInteraction materialTextView = Espresso.onData(Matchers.anything())
                .inAdapterView(childAtPosition(
                        ViewMatchers.withClassName(Matchers.is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(6);
        materialTextView.perform(ViewActions.click());

        ViewInteraction appCompatEditText3 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.course_number_edit_text), ViewMatchers.withText("167"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                3),
                        ViewMatchers.isDisplayed()));
        appCompatEditText3.perform(ViewActions.click());

        ViewInteraction appCompatEditText4 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.course_number_edit_text), ViewMatchers.withText("167"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                3),
                        ViewMatchers.isDisplayed()));
        appCompatEditText4.perform(ViewActions.replaceText(""));

        ViewInteraction appCompatEditText5 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.course_number_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                3),
                        ViewMatchers.isDisplayed()));
        appCompatEditText5.perform(ViewActions.closeSoftKeyboard());

        ViewInteraction materialButton3 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.save_class_button), ViewMatchers.withText("Save Class"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                0),
                        ViewMatchers.isDisplayed()));
        materialButton3.perform(ViewActions.click());

        ViewInteraction materialButton4 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.go_back_btnAACP), ViewMatchers.withText("Go Back"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                9),
                        ViewMatchers.isDisplayed()));
        materialButton4.perform(ViewActions.click());

        ViewInteraction textView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.course_edit_text), ViewMatchers.withText("CSE  Winter 2022"),
                        ViewMatchers.withParent(ViewMatchers.withParent(ViewMatchers.withId(R.id.parent_linear_layout))),
                        ViewMatchers.isDisplayed()));
        textView.check(ViewAssertions.matches(ViewMatchers.withText("CSE  Winter 2022")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
