package com.example.birds_of_a_feather_cse_110_team_33;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class AddAClassPage extends AppCompatActivity {

    private LinearLayout parentLinearLayout;



    public final String[] years = new String[]{"2016","2017","2018","2019","2020","2021","2022"};
    public final String[] quarters = new String[]{"Winter","Spring","Summer 1","Summer 2","SSS","Fall"};

    public static final String EXTRA_DATA = "EXTRA_DATA";

    public int saveClicks = 0;
    public int numFromListDisplay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aclass_page);

        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        //Grab how many we have created thus far, so we know where to start from.
        numFromListDisplay = getIntent().getIntExtra("addNumB",0);

        saveClicks = numFromListDisplay;
        setTextWithPreviousEntry();

    }

    public void setTextWithPreviousEntry() {
        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        TextView courseName = (TextView) findViewById(R.id.course_subject_edit_text);
        TextView courseNum = (TextView) findViewById(R.id.course_number_edit_text);
        Spinner season = (Spinner) findViewById(R.id.quarters_spinner);
        Spinner year = (Spinner) findViewById(R.id.years_spinner);

        int seasonChoice = season.getSelectedItemPosition();
        int yearChoice = year.getSelectedItemPosition();

        courseName.setText(courseName.getText().toString());
        courseNum.setText(courseNum.getText().toString());
        season.setSelection(seasonChoice);
        year.setSelection(yearChoice);
    }


    public void onSaveClassClicked(View view) {


        //Add new class to the User/Person #0 object, and add the class to the course database


        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        TextView courseName = (TextView) findViewById(R.id.course_subject_edit_text);
        TextView courseNum = (TextView) findViewById(R.id.course_number_edit_text);
        Spinner season = (Spinner) findViewById(R.id.quarters_spinner);
        Spinner year = (Spinner) findViewById(R.id.years_spinner);

        int seasonChoice = season.getSelectedItemPosition();
        int yearChoice = year.getSelectedItemPosition();


        //Somehow add the row of new info into the ListDisplay.class file
        String toSend = courseName.getText().toString() + " " + courseNum.getText().toString() + " " + quarters[seasonChoice] + " " + years[yearChoice];

        editor.putString("classRow" + saveClicks,toSend);

        editor.apply();


        // Also keep the info thats filled in for when the user comes back.
        courseName.setText(courseName.getText().toString());
        courseNum.setText(courseNum.getText().toString());
        season.setSelection(seasonChoice);
        year.setSelection(yearChoice);

        saveClicks++;

    }



    public void onGoBackAACPClicked(View view) {

        Intent intent = new Intent(this, UserClassList.class);
        intent.putExtra("addNum",saveClicks);
        setResult(RESULT_OK, intent);

        SharedPreferences preferences = getSharedPreferences("prof one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("addNum",saveClicks);



        startActivity(intent);
    }
}