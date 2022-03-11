package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.birds_of_a_feather_cse_110_team_33.filtering.*;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;


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
    private Spinner filterSpinner;
    private AppDatabase db;
    private int userId;

    private int sessionCount;
    private boolean approvalToLoadNewSession;
    private int sessionToLoad;
    private PersonDao personDao;
    private CoursesDao coursesDao;



    private IFilter filter;
    private Person user;


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



        filterSpinner = findViewById(R.id.filters_spinner);



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




        user = personDao.get(userId);

        setTitle(user.getName() + "'s Birds of a Feather");

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                List<Person> person = db.personDao().getAll();
                //remove user from persons list
                for (Person ppl: person) {
                    if (ppl.getPersonId() == userId) {
                        person.remove(ppl);
                        break;
                    }
                }
                setPersonNumShared(person, user);

                switch(position) {
                    case 0:
                        filter = new TotalFilter();
                        break;
                    case 1:
                        filter = new CurrentFilter();
                        break;
                    case 2:
                        filter = new SizeFilter(getApplicationContext(), userId);
                        break;
                    case 3:
                        filter = new RecencyFilter(getApplicationContext(), userId);
                        break;
                }
                filter.filter(person);
                personsViewAdapter = new PersonsViewAdapter(person, userId);
                personsRecyclerView.setAdapter(personsViewAdapter);

                Toast.makeText(getApplicationContext(),"Filtering...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                List<Person> person = db.personDao().getAll();
                //remove user from persons list
                for (Person ppl: person) {
                    if (ppl.getPersonId() == userId) {
                        person.remove(ppl);
                        break;
                    }
                }
                setPersonNumShared(person, user);

                filter = new TotalFilter();
                filter.filter(person);
                personsViewAdapter = new PersonsViewAdapter(person, userId);
                personsRecyclerView.setAdapter(personsViewAdapter);
            }

        });


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
        Button b = findViewById(R.id.start_stop);
        String text = b.getText().toString();
        if (text.equals("Start")) {
            b.setText("Stop");
            personsRecyclerView.setVisibility(View.VISIBLE);
        } else if (text.equals("Stop")) {
            b.setText("Start");
            personsRecyclerView.setVisibility(View.GONE);
        }
    }

    public void onMyFavoriteClicked(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, FavoriteListActivity.class);
        intent.putExtra("user", userId);
        context.startActivity(intent);
    }

    public void setPersonNumShared(List<Person> persons, Person user){
        for(Person person: persons) {
            person.setNumShared(db.personDao().
                                getSharedCourses(person.getPersonId(), user.getPersonId()).size());
        }

        for(Person person: persons) {
            person.setCurrentShared(db.personDao().
                    getCurrentSharedCourses(person.getPersonId(), user.getPersonId(),
                            getString(R.string.current_qtr), getResources().getInteger(R.integer.current_year)).size());
        }
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

       /* List<List<Course>> coursesToLoad = new ArrayList<>();

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
        }*/

        //We grabbed all the info we need, now simply load it on the page, and wipe the previous data from the database.



        // What we are technically supposed to do
        personsViewAdapter = new PersonsViewAdapter(personsToLoad, userId);
        personsRecyclerView.setAdapter(personsViewAdapter);






    }

}