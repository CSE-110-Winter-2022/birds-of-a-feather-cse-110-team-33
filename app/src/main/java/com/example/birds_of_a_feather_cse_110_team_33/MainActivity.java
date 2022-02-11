package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;



import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.CoursesDao;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

import com.example.birds_of_a_feather_cse_110_team_33.UserClassList;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    protected AppDatabase db;
    protected PersonDao personDao;
    protected CoursesDao coursesDao;

    protected Person ethan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BoF Start");

        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //editor.clear();
        //editor.apply();

        AppDatabase.useTestSingleton(this);
        db = AppDatabase.singleton(this);
        personDao = db.personDao();
        coursesDao = db.coursesDao();

        // some testing pre-population
        // base
        ethan = new Person("Ethan", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        personDao.insert(ethan);

        editor.putInt("userId",ethan.getPersonId());
       // Course ethan110 = new Course(ethan.getPersonId(), 2021, "Winter", "CSE", "110");
       // Course ethan112 = new Course(ethan.getPersonId(), 2021, "Winter", "CSE", "112");
       // Course ethan132A = new Course(ethan.getPersonId(), 2021, "Spring", "CSE", "132A");
       // coursesDao.insert(ethan110);
       // coursesDao.insert(ethan112);
       // coursesDao.insert(ethan132A);

        // share all
        Person james = new Person("James", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        personDao.insert(james);
        Course james110 = new Course(james.getPersonId(), 2021, "Winter", "CSE", "110");
        Course james112 = new Course(james.getPersonId(), 2021, "Winter", "CSE", "112");
        Course james132A = new Course(james.getPersonId(), 2021, "Spring", "CSE", "132A");
        coursesDao.insert(james110);
        coursesDao.insert(james112);
        coursesDao.insert(james132A);

        // share none
        Person nick = new Person("Nick", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        personDao.insert(nick);
        Course nick110 = new Course(nick.getPersonId(), 2022, "Winter", "CSE", "110");
        Course nick112 = new Course(nick.getPersonId(), 2022, "Winter", "CSE", "112");
        Course nick132A = new Course(nick.getPersonId(), 2022, "Spring", "CSE", "132A");
        coursesDao.insert(nick110);
        coursesDao.insert(nick112);
        coursesDao.insert(nick132A);

        // share one
        Person ryan = new Person("Ryan", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        personDao.insert(ryan);
        Course ryan110 = new Course(ryan.getPersonId(), 2021, "Winter", "CSE", "110");
        Course ryan112 = new Course(ryan.getPersonId(), 2022, "Winter", "CSE", "112");
        Course ryan132A = new Course(ryan.getPersonId(), 2022, "Spring", "CSE", "132A");
        coursesDao.insert(ryan110);
        coursesDao.insert(ryan112);
        coursesDao.insert(ryan132A);
    }

    public void onStartClicked(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, HomePageActivity.class);
        context.startActivity(intent);
    }



    public void onClassBtnClicked(View view) {
        Context context = view.getContext();
        Intent intent  = new Intent(context,UserClassList.class);
        intent.putExtra("Ethan id",ethan.getPersonId());

        context.startActivity(intent);
    }



}