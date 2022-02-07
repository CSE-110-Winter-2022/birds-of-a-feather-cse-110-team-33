package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private RecyclerView personsRecyclerView;
    private RecyclerView.LayoutManager personsLayoutManager;
    private PersonsViewAdapter personsViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setTitle("Birds of a Feather");

        AppDatabase db = AppDatabase.singleton(this);
        List<Person> persons = db.personDao().getAll();

        personsRecyclerView = findViewById(R.id.persons_view);
        personsLayoutManager = new LinearLayoutManager(this);
        personsRecyclerView.setLayoutManager(personsLayoutManager);

        personsViewAdapter = new PersonsViewAdapter(persons);
        personsRecyclerView.setAdapter(personsViewAdapter);
    }


    public void onStartStopClicked(View view) {
        // implementation for User Story: ON/OFF Search
    }
}

