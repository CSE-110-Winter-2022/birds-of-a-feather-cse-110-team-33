package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.birds_of_a_feather_cse_110_team_33.model.DummyPerson;
import com.example.birds_of_a_feather_cse_110_team_33.model.IPerson;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonWithCourses;

import java.util.Arrays;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private AppDatabase db;
    private IPerson person;

    private RecyclerView personsRecyclerView;
    private RecyclerView.LayoutManager personsLayoutManager;
    private PersonsViewAdapter personsViewAdapter;

    protected IPerson[] persons = {
            //
            // base user
            new DummyPerson(1, "Ethan", new Course[] {
                    new Course(0, 1, 2021, "Winter", "CSE", "110"),
                    new Course(1, 1, 2021, "Winter", "CSE", "112"),
                    new Course(2, 1, 2021, "Spring", "CSE", "132A")}
                    , "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png"),

            // shares all courses with Ethan
            new DummyPerson(2, "James", new Course[] {
                    new Course(4, 2, 2021, "Winter", "CSE", "110"),
                    new Course(5, 2, 2021, "Winter", "CSE", "112"),
                    new Course(6, 2, 2021, "Spring", "CSE", "132A")}
                    , "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png"),

            // shares no courses with Ethan
            new DummyPerson(3, "Ryan", new Course[] {
                    new Course(7, 3, 2022, "Winter", "CSE", "110"),
                    new Course(8, 3, 2022, "Winter", "CSE", "112"),
                    new Course(9, 3, 2022, "Spring", "CSE", "132A")}
                    , "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png"),

            // shares 1 course with Ethan
            new DummyPerson(4, "Nick", new Course[] {
                    new Course(10, 4, 2021, "Winter", "CSE", "110"),
                    new Course(11, 4, 2022, "Winter", "CSE", "112"),
                    new Course(12, 4, 2022, "Spring", "CSE", "132A")}
                    , "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setTitle("Birds of a Feather");

       /*
       This stuff is used when coming from the previous page
       Gonna just use/initialize db directly rn instead

       Intent intent = getIntent();
       int personId = intent.getIntExtra("person_id", 0);

       // db stuff we should implement (basic structure/thoughts shown above)
       db = AppDatabase.singleton(this);
       person = db.personsWithCoursesDao().get(personId);
       List<Course> courses = db.coursesDao().getForPerson(personId);


       db = AppDatabase.singleton(this);
       List<? extends IPerson> persons = db.personsWithCoursesDao().getAll();
       */

        personsRecyclerView = findViewById(R.id.persons_view);
        personsLayoutManager = new LinearLayoutManager(this);
        personsRecyclerView.setLayoutManager(personsLayoutManager);

        personsViewAdapter = new PersonsViewAdapter(Arrays.asList(persons));
        personsRecyclerView.setAdapter(personsViewAdapter);
    }


    public void onStartStopClicked(View view) {
        // implementation for User Story: ON/OFF Search
    }
}
