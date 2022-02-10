package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.List;

public class PersonDetailActivity extends AppCompatActivity {
    private AppDatabase db;
    private Person person;

    private RecyclerView profileRecyclerView;
    private RecyclerView.LayoutManager profileLayoutManager;
    private ProfileViewAdapter profileViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        Intent intent = getIntent();
        int personId = intent.getIntExtra("person_id", 0);

        db = AppDatabase.singleton(this);
        person = db.personDao().get(personId);
        List<Course> courses = db.coursesDao().getForPerson(personId);

        // set the title with the person
        setTitle(person.getName() + " Profile");

        // set up the recycler view to show our database contents
        profileRecyclerView = findViewById(R.id.shared_courses);
        profileLayoutManager = new LinearLayoutManager(this);
        profileRecyclerView.setLayoutManager(profileLayoutManager);

        profileViewAdapter = new ProfileViewAdapter(courses);
        profileRecyclerView.setAdapter(profileViewAdapter);
    }

    public void onGoBackClicked(View view) {
        finish();
    }
}