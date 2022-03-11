package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.CoursesDao;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;
import com.google.gson.Gson;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    protected AppDatabase db;
    protected PersonDao personDao;
    protected CoursesDao coursesDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BoF Start");




        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        //Reset all of the data in SharedPreferences before starting.
        editor.clear();




        AppDatabase.useTestSingleton(this);
        db = AppDatabase.singleton(this);
        personDao = db.personDao();
        coursesDao = db.coursesDao();

        // share all
        Person james = new Person("James", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        james.setPersonId(personDao.maxId() + 1);
        james.setFavorite();
        personDao.insert(james);
        Course james110 = new Course(james.getPersonId(), 2022, "Winter", "CSE", "110", "Tiny");
        Course james112 = new Course(james.getPersonId(), 2022, "Winter", "CSE", "112", "Small");
        Course james132A = new Course(james.getPersonId(), 2022, "Spring", "CSE", "132A", "Large");
        coursesDao.insert(james110);
        coursesDao.insert(james112);
        coursesDao.insert(james132A);

        // share none
        Person nick = new Person("Nick", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        nick.setPersonId(personDao.maxId() + 1);
        nick.setNFavorite();
        personDao.insert(nick);
        Course nick110 = new Course(nick.getPersonId(), 2021, "Winter", "CSE", "110", "Tiny");
        Course nick112 = new Course(nick.getPersonId(), 2021, "Winter", "CSE", "112", "Small");
        Course nick132A = new Course(nick.getPersonId(), 2021, "Spring", "CSE", "132A", "Large");
        coursesDao.insert(nick110);
        coursesDao.insert(nick112);
        coursesDao.insert(nick132A);

        // share two
        Person ryan = new Person("Ryan", "https://i.kym-cdn.com/photos/images/original/001/431/201/40f.png");
        ryan.setPersonId(personDao.maxId() + 1);
        ryan.setFavorite();
        personDao.insert(ryan);
        Course ryan110 = new Course(ryan.getPersonId(), 2022, "Winter", "CSE", "110", "Tiny");
        Course ryan112 = new Course(ryan.getPersonId(), 2021, "Winter", "CSE", "112", "Small");
        Course ryan132A = new Course(ryan.getPersonId(), 2022, "Spring", "CSE", "132A", "Large");
        coursesDao.insert(ryan110);
        coursesDao.insert(ryan112);
        coursesDao.insert(ryan132A);


    }

    public void onClassBtnClicked(View view) {

        BluetoothPopup popUpClass = new BluetoothPopup();
        popUpClass.showPopupWindow(view);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Context context = view.getContext();
                Intent intent = new Intent(context, ConfirmNameActivity.class);
                context.startActivity(intent);
            }}, 5000);

    }

}
