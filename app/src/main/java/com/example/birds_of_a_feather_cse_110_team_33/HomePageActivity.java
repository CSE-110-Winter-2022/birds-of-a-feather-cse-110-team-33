package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity {
    private RecyclerView personsRecyclerView;
    private RecyclerView.LayoutManager personsLayoutManager;
    private PersonsViewAdapter personsViewAdapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setTitle("Birds of a Feather");

        db = AppDatabase.singleton(this);
        List<Person> persons = db.personDao().getAll();


        personsRecyclerView = findViewById(R.id.persons_view);
        personsLayoutManager = new LinearLayoutManager(this);
        personsRecyclerView.setLayoutManager(personsLayoutManager);

        //remove user from persons list
        Person user = persons.remove(0);

        //fill Person.num_shared and short
        setPersonNumShared(persons, user);
        sortPersonsByNumShared(persons);

        personsViewAdapter = new PersonsViewAdapter(persons);
        personsRecyclerView.setAdapter(personsViewAdapter);

    }


    public void onStartStopClicked(View view) {
        // implementation for User Story: ON/OFF Search
    }

    public void setPersonNumShared(List<Person> persons, Person user){
        for(Person person: persons){
            person.setNumShared(db.personDao().
                                getSharedCourses(person.getPersonId(),user.getPersonId()).size());
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