package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.birds_of_a_feather_cse_110_team_33.filtering_sorting.IFilter;
import com.example.birds_of_a_feather_cse_110_team_33.filtering_sorting.ISorter;
import com.example.birds_of_a_feather_cse_110_team_33.filtering_sorting.SizeSorter;
import com.example.birds_of_a_feather_cse_110_team_33.filtering_sorting.TotalFilter;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private RecyclerView personsRecyclerView;
    private RecyclerView.LayoutManager personsLayoutManager;
    private PersonsViewAdapter personsViewAdapter;
    private AppDatabase db;
    private int userId;
    private ISorter sorter;
    private IFilter filter;

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
        setPersonNumShared(persons, user);

        // TODO: factory method to create the proper filter based on user choice
        filter = new TotalFilter();
        filter.filter(persons);

        sorter = new SizeSorter();
        sorter.sort(persons);

        personsViewAdapter = new PersonsViewAdapter(persons, userId);
        personsRecyclerView.setAdapter(personsViewAdapter);
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
        for(Person person: persons){
            person.setNumShared(db.personDao().
                                getSharedCourses(person.getPersonId(), user.getPersonId()).size());
        }
    }
}