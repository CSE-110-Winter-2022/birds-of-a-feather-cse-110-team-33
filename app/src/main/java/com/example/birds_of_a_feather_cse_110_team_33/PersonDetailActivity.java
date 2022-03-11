package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.List;

public class PersonDetailActivity extends AppCompatActivity {
    private AppDatabase db;
    private Person person;
    private int userId;

    private RecyclerView profileRecyclerView;
    private RecyclerView.LayoutManager profileLayoutManager;
    private ProfileViewAdapter profileViewAdapter;
    private TextView name;
    private ToggleButton favButton;
    private TextView photo;
    private ImageButton waveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        Intent intent = getIntent();
        int personId = intent.getIntExtra("person_id", 0);
        int userId = intent.getIntExtra("user", 0);

        db = AppDatabase.singleton(this);
        person = db.personDao().get(personId);

        List<Course> sharedCourses = db.personDao().getSharedCourses(personId, userId);

        setTitle(person.getName() + "'s Profile");

        // set profile name
        name = findViewById(R.id.profile_name);
        photo = findViewById(R.id.profile_pic);
        photo.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_pp));
        name.setText(person.getName());

        // Favorite Button Functionality
        favButton = (ToggleButton) findViewById(R.id.favorite);
        favButton.setChecked(person.isFavorite);
        favButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fav_off));

        // Wave Button Functionality
        waveBtn = (ImageButton) findViewById(R.id.wave);
        waveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence text = "Waved";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        // Initialize correct button states
        if(person.isFavorite) {
            favButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fav_on));
        } else {
            favButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fav_off));
        }
        favButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence text;
                if (isChecked) {
                    favButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fav_on));
                    person.setFavorite();
                    text = "Favorite Added";
                } else {
                    favButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fav_off));
                    person.setNFavorite();
                    text = "Favorite Removed";
                }
                db.personDao().update(person);
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        // set profile picture, not working yet
        // photo.setImageBitmap(person.getString());

        // set up the recycler view to show our database contents
        profileRecyclerView = findViewById(R.id.shared_courses);
        profileLayoutManager = new LinearLayoutManager(this);
        profileRecyclerView.setLayoutManager(profileLayoutManager);

        profileViewAdapter = new ProfileViewAdapter(sharedCourses);
        profileRecyclerView.setAdapter(profileViewAdapter);
    }

    public void onGoBackClicked(View view) {
        finish();
    }
}