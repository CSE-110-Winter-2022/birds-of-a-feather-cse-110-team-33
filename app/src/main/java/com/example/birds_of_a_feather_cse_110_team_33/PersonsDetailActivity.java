package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.birds_of_a_feather_cse_110_team_33.model.IPerson;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;

import java.util.Arrays;
import java.util.List;

public class PersonsDetailActivity extends AppCompatActivity {
    private AppDatabase db;
    private IPerson person;

    private RecyclerView profileRecyclerView;
    private RecyclerView.LayoutManager profileLayoutManager;
    private ProfileViewAdapter profileViewAdapter;

    Course[] courses = {new Course(0, 1, 2021, "Winter", "CSE", "110"),
            new Course(1, 1, 2021, "Winter", "CSE", "112"),
            new Course(2, 1, 2021, "Spring", "CSE", "132A")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_detail);

        //Intent intent = getIntent();
        //int personId = intent.getIntExtra("person_id", 0);

       /*
       db = AppDatabase.singleton(this);
       person = db.personsWithCoursesDao().get(personId);
       List<Course> courses = db.coursesDao().getForPerson(personId);
       */

        // set the title with the person
        // setTitle(person.getName());

        // set up the recycler view to show our database contents
        profileRecyclerView = findViewById(R.id.shared_courses);
        profileLayoutManager = new LinearLayoutManager(this);
        profileRecyclerView.setLayoutManager(profileLayoutManager);

        profileViewAdapter = new ProfileViewAdapter(Arrays.asList(courses));
        profileRecyclerView.setAdapter(profileViewAdapter);
    }

    public void onGoBackClicked(View view) {
        finish();
    }
}
