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
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private RecyclerView personsRecyclerView;
    private RecyclerView.LayoutManager personsLayoutManager;
    private PersonsViewAdapter personsViewAdapter;
    private AppDatabase db;
    private int userId;
    private int sessionCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        db = AppDatabase.singleton(this);
        List<Person> persons = db.personDao().getAll();
        userId = getIntent().getIntExtra("user",1);

        personsRecyclerView = findViewById(R.id.persons_view);
        personsLayoutManager = new LinearLayoutManager(this);
        personsRecyclerView.setLayoutManager(personsLayoutManager);


        //remove user from persons list
        for (Person person: persons) {
            if (person.getPersonId() == userId) {
                persons.remove(person);
                break;
            }
        }





        Person user = db.personDao().get(userId);
        setTitle(user.getName() + "'s Birds of a Feather");

        //fill Person.num_shared and short
        setPersonNumShared(persons, user);
        sortPersonsByNumShared(persons);

        personsViewAdapter = new PersonsViewAdapter(persons, userId);
        personsRecyclerView.setAdapter(personsViewAdapter);
    }

    public void saveSession(View v) {

        //Increment Session Count
        sessionCount++;


        int coursesCount = 0;

        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("sessionCount" + sessionCount,sessionCount);


        //Code for Saving Session Start -- Save Previous List
        List<Person> tempList = db.personDao().getAll();

        Gson gson = new Gson();
        String json = gson.toJson(tempList);
        editor.putString("savedPersons", json);
        editor.commit();
        //Code for Saving Session End -- Save Previous List



        //Code for Saving Session Start -- Save Previous Course List
        for (int i = 0; i < tempList.size(); i++) {
            List<Course> tempCourseList = db.coursesDao().getForPerson(tempList.get(i).personId);

            Gson gsonTemp = new Gson();
            String jsonTemp = gsonTemp.toJson(tempCourseList);
            editor.putString("savedCourses" + i, jsonTemp);
            editor.commit();
            coursesCount++;
        }
        //Code for Saving Session Start -- Save Previous Course List

        editor.putInt("coursesCount" + sessionCount,coursesCount);
        Intent intent = new Intent(this, SaveSessionActivity.class);

        intent.putExtra("sessionCount",sessionCount);


        startActivity(intent);





    }


    public void onStartStopClicked(View view) {
        // implementation for User Story: ON/OFF Search


    }

    public void setPersonNumShared(List<Person> persons, Person user){
        for(Person person: persons){
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

}