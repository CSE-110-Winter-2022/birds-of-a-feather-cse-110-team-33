package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.CoursesDao;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private RecyclerView personsRecyclerView;
    private RecyclerView.LayoutManager personsLayoutManager;
    private PersonsViewAdapter personsViewAdapter;
    private AppDatabase db;
    private int userId;
    private int sessionCount;
    private boolean approvalToLoadNewSession;
    private int sessionToLoad;
    private PersonDao personDao;
    private CoursesDao coursesDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        sessionCount = preferences.getInt("sessionCountPref",0);

        //Only when coming from "UserClassList" Does this become 0, if not its 1, and thus
        // its not the first time we loaded this page.
        int initialCount = getIntent().getIntExtra("initialCount",1);


        db = AppDatabase.singleton(this);

        personDao = db.personDao();
        coursesDao = db.coursesDao();

        List<Person> persons = db.personDao().getAll();
        userId = getIntent().getIntExtra("user",1);

        personsRecyclerView = findViewById(R.id.persons_view);
        personsLayoutManager = new LinearLayoutManager(this);
        personsRecyclerView.setLayoutManager(personsLayoutManager);


        if (initialCount  != 0) {
            Person userToSave = personDao.get(userId);
            Gson gson = new Gson();
            String json = gson.toJson(userToSave);
            editor.putString("original user", json);
            editor.commit();
        }



        //remove user from persons list
        for (Person person: persons) {
            if (person.getPersonId() == userId) {
                persons.remove(person);
                break;
            }
        }




        //New Way to grab user
        Gson gsonUser = new Gson();
        String jsonUser = preferences.getString("original user","");
        Person user = gsonUser.fromJson(jsonUser,Person.class);


        //2nd Time this is ran, its a null object
        setTitle(user.getName() + "'s Birds of a Feather");

        //fill Person.num_shared and short
        setPersonNumShared(persons, user);
        sortPersonsByNumShared(persons);

        personsViewAdapter = new PersonsViewAdapter(persons, userId);
        personsRecyclerView.setAdapter(personsViewAdapter);

        approvalToLoadNewSession = preferences.getBoolean("approvalToLoad",false);
        sessionToLoad = preferences.getInt("sessionToLoad",0);

        if (initialCount != 0) {
            loadSession(approvalToLoadNewSession,sessionToLoad);
        }

    }

    public void saveSession(View v) {

        //Increment Session Count
        sessionCount++;


        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        //Code for Saving Session Start -- Save Previous List
        List<Person> tempList = db.personDao().getAll();

        Gson gson = new Gson();
        String json = gson.toJson(tempList);
        editor.putString("savedPersons"+sessionCount, json);
        editor.commit();
        //Code for Saving Session End -- Save Previous List



        //Code for Saving Session Start -- Save Previous Course List
        for (int i = 0; i < tempList.size(); i++) {
            List<Course> tempCourseList = db.coursesDao().getForPerson(tempList.get(i).personId);

            Gson gsonTemp = new Gson();
            String jsonTemp = gsonTemp.toJson(tempCourseList);

            //Save a list of courses with its corresponding session
            editor.putString("savedCourses" + i + "Session" + sessionCount, jsonTemp);
            editor.commit();

        }
        //Code for Saving Session Start -- Save Previous Course List

        editor.putInt("personsCount" + sessionCount,tempList.size());

        //Store session Count
        editor.putInt("sessionCountPref",sessionCount);

        editor.commit();

        Intent intent = new Intent(this, SaveSessionActivity.class);




        startActivity(intent);





    }


    public void onStartStopClicked(View view) {
        // implementation for User Story: ON/OFF Search


    }

    public void setPersonNumShared(List<Person> persons, Person user) {
        for(Person person: persons) {
            person.setNumShared(db.personDao().
                                getSharedCourses(person.getPersonId(), user.getPersonId()).size());
        }
    }

    public void sortPersonsByNumShared(List<Person> persons){
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p2.getNumShared() - p1.getNumShared();
            }
        });
    }

    public void loadSessionBtnClicked(View view) {

        //Move to Load Session Page, do work

        Intent intent = new Intent(this, LoadSessionActivity.class);

        intent.putExtra("sessionCount",sessionCount);



        startActivity(intent);
    }



    public void loadSession(boolean approval, int sessionNumber) {

        if (approval == false || sessionNumber == 0) {
            return;
        }

        //personDao.nukeTable();
        //coursesDao.nukeTable();
        //db.clearAllTables();
        List<Person> persons = db.personDao().getAll();

        //Remove all the old people and old courses
        for (Person person : persons) {
            List<Course> courses = db.coursesDao().getForPerson(person.getPersonId());
            for (Course currCourse: courses) {
                coursesDao.delete(currCourse);
            }
            personDao.delete(person);
        }





        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gsonUser = new Gson();
        String jsonUser = preferences.getString("original user","");
        Person userToLoad = gsonUser.fromJson(jsonUser,Person.class);

        personDao.insert(userToLoad);

        //Figure out how ethan is loading info, and then load it.

        Gson gson = new Gson();
        String json = preferences.getString("savedPersons" + sessionNumber, "");

        //Necessary for lists
        Type personListType = new TypeToken<List<Person>>() {}.getType();

        List<Person> personsToLoad = gson.fromJson(json, personListType);

        List<List<Course>> coursesToLoad = new ArrayList<>();

        int coursesForSessionToLoad = personsToLoad.size();

        for (int i = 0; i < coursesForSessionToLoad; i++) {
            Gson gson2 = new Gson();
            // "i" = current person we are grabbing a list of courses from, and "sessionNumber" is the session we are grabbing this from
            String json2 = preferences.getString("savedCourses" + i + "Session" + sessionNumber,"");
            //Necessary for lists
            Type courseListType = new TypeToken<List<Course>>() {}.getType();

            List<Course> courseToLoad = gson2.fromJson(json2,courseListType);

            //Fill up our list of lists with each course
            coursesToLoad.add(courseToLoad);
        }

        //We grabbed all the info we need, now simply load it on the page, and wipe the previous data from the database.


        //Fill With People
        for (int i = 0; i < personsToLoad.size(); i++) {
            personDao.insert(personsToLoad.get(i));
        }

        //Fill with Courses Corresponding to people

        for (int i = 0; i < personsToLoad.size(); i++) {

            List<Course> currCourseList = coursesToLoad.get(i);

            for (int j = 0; j < currCourseList.size(); j++) {
                coursesDao.insert(currCourseList.get(j));
            }
        }


        //People and Courses Filled, Reload Page
        //Waiting on Ethan to do so

        int userToLoadId = userToLoad.getPersonId();
        // What we are technically supposed to do
        personsViewAdapter = new PersonsViewAdapter(personsToLoad, userToLoadId);
        personsRecyclerView.setAdapter(personsViewAdapter);






    }
}