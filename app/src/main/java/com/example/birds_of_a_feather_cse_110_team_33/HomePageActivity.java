package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.birds_of_a_feather_cse_110_team_33.filtering.*;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private RecyclerView personsRecyclerView;
    private RecyclerView.LayoutManager personsLayoutManager;
    private PersonsViewAdapter personsViewAdapter;
    private Spinner filterSpinner;
    private AppDatabase db;
    private int userId;
    private IFilter filter;
    private Person user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        db = AppDatabase.singleton(this);
        userId = getIntent().getIntExtra("user",1);

        personsRecyclerView = findViewById(R.id.persons_view);
        personsLayoutManager = new LinearLayoutManager(this);
        personsRecyclerView.setLayoutManager(personsLayoutManager);
        filterSpinner = findViewById(R.id.filters_spinner);

        user = db.personDao().get(userId);
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
    }


    public void onStartStopClicked(View view) {
        Button b = findViewById(R.id.start_stop);
        String text = b.getText().toString();

        if (text.equals("Start")) {
            b.setText("Stop");
            personsRecyclerView.setVisibility(View.VISIBLE);
        }
        else if (text.equals("Stop")) {
            b.setText("Start");
            personsRecyclerView.setVisibility(View.GONE);
        }
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


}