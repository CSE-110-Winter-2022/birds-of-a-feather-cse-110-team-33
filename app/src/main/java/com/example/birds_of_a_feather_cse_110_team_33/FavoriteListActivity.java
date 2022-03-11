package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.List;

public class FavoriteListActivity extends AppCompatActivity {
    private RecyclerView personsRecyclerView;
    private RecyclerView.LayoutManager personsLayoutManager;
    private PersonsViewAdapter personsViewAdapter;
    private AppDatabase db;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        db = AppDatabase.singleton(this);
        List<Person> persons = db.personDao().getAll();
        userId = getIntent().getIntExtra("user",1);

        personsRecyclerView = findViewById(R.id.favorites_view);
        personsLayoutManager = new LinearLayoutManager(this);
        personsRecyclerView.setLayoutManager(personsLayoutManager);

        // remove non-favorites
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).isFavorite == false) {
                persons.remove(persons.get(i));
                i--;
            }
        }
        Person user = db.personDao().get(userId);
        setPersonNumShared(persons,user);

        personsViewAdapter = new PersonsViewAdapter(persons, userId);
        personsRecyclerView.setAdapter(personsViewAdapter);

    }

    public void onBackClicked(View view) {
        /*Context context = view.getContext();
        Intent intent = new Intent(context, HomePageActivity.class);
        intent.putExtra("user", userId);
        context.startActivity(intent);*/
        finish();
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