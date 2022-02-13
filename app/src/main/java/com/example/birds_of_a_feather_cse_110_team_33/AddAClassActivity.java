package com.example.birds_of_a_feather_cse_110_team_33;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.CoursesDao;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

public class AddAClassActivity extends AppCompatActivity {

    private LinearLayout parentLinearLayout;



    public final String[] years = new String[]{"2016","2017","2018","2019","2020","2021","2022"};
    public final String[] quarters = new String[]{"Winter","Spring","Summer_1","Summer_2","SSS","Fall"};



    public int saveClicks = 0;
    public int numFromListDisplay;
    public int userIdd;
    protected AppDatabase db;
    protected PersonDao personDao;
    protected CoursesDao coursesDao;
    public String courseSubject;
    public String courseNumber;
    public int quarterSpinnerChoice;
    public int yearSpinnerChoice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aclass_page);

        //Check with ethan to see if this works or not.

        db = AppDatabase.singleton(this);
        personDao = db.personDao();
        coursesDao = db.coursesDao();

        parentLinearLayout = findViewById(R.id.parent_linear_layout);
        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);

        //Grab how many we have created thus far, so we know where to start from.
        numFromListDisplay = getIntent().getIntExtra("addNumB",0);
        userIdd = getIntent().getIntExtra("Ethan id",0);

        saveClicks = numFromListDisplay;

        courseSubject = getIntent().getStringExtra("courseSubject");
        courseNumber = getIntent().getStringExtra("courseNumber");
        quarterSpinnerChoice = getIntent().getIntExtra("quarterSpinnerChoice",0);
        yearSpinnerChoice = getIntent().getIntExtra("yearSpinnerChoice",0);

        if (numFromListDisplay > 0) {
            setTextWithPreviousEntry();
        }
    }

    public void setTextWithPreviousEntry() {

        TextView courseName = (TextView) findViewById(R.id.course_subject_edit_text);
        TextView courseNum = (TextView) findViewById(R.id.course_number_edit_text);
        Spinner season = (Spinner) findViewById(R.id.quarters_spinner);
        Spinner year = (Spinner) findViewById(R.id.years_spinner);

        courseName.setText(courseSubject);
        courseNum.setText(courseNumber);
        season.setSelection(quarterSpinnerChoice);
        year.setSelection(yearSpinnerChoice);


    }


    public void onSaveClassClicked(View view) {
        if (saveClicks == 11) {
            Toast.makeText(AddAClassActivity.this,"Page Limit Reached!", Toast.LENGTH_SHORT).show();

            return;
        }
        //Add new class to the User/Person #0 object, and add the class to the course database


        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        TextView courseName = (TextView) findViewById(R.id.course_subject_edit_text);
        TextView courseNum = (TextView) findViewById(R.id.course_number_edit_text);
        Spinner season = (Spinner) findViewById(R.id.quarters_spinner);
        Spinner year = (Spinner) findViewById(R.id.years_spinner);

        int seasonChoice = season.getSelectedItemPosition();
        int yearChoice = year.getSelectedItemPosition();



        String courseNameString = courseName.getText().toString();

        //Convert string to all uppercase.
        courseNameString = courseNameString.toUpperCase();

        //Make sure there are no leading or trailing spaces
        courseNameString = courseNameString.trim();

        //Remove any spaces in the entered string
        courseNameString = courseNameString.replaceAll(" ","");


        String courseNumString = courseNum.getText().toString();



        if (courseNameString.equals("") || courseNumString.equals("")) {
            Toast.makeText(AddAClassActivity.this,"Cannot Have Empty Fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (courseNameString.length() > 4 || courseNameString.length() < 3) {
            Toast.makeText(AddAClassActivity.this,"Course Subject must be between 3 and 4 Letters Long!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (courseNumString.length() > 4 || courseNumString.length() < 3) {
            Toast.makeText(AddAClassActivity.this,"Course Number must be between 3 and 4 Characters Long!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (courseNumString.length() > 4 || courseNumString.length() < 3) {
            Toast.makeText(AddAClassActivity.this,"Course Number must be between 3 and 4 Characters Long!", Toast.LENGTH_SHORT).show();
            return;
        }


        char CNSArray[] = courseNameString.toCharArray();

        for (int i = 0; i < CNSArray.length; i++) {
            if (CNSArray[i] < 65 || CNSArray[i] > 90) {
                Toast.makeText(AddAClassActivity.this,"Course Subject can only contain Letters!", Toast.LENGTH_SHORT).show();
                return;
            }
        }









        //Somehow add the row of new info into the ListDisplay.class file
        String toSend = courseNameString + " " + courseNum.getText().toString() + " " + quarters[seasonChoice] + " " + years[yearChoice];

        editor.putString("classRow" + saveClicks,toSend);

        editor.apply();

        Course newCourse = new Course(userIdd,Integer.parseInt(years[yearChoice]),quarters[seasonChoice],courseNameString,courseNum.getText().toString());
        coursesDao.insert(newCourse);

        // Also keep the info thats filled in for when the user comes back.
        courseName.setText(courseName.getText().toString());
        courseNum.setText(courseNum.getText().toString());
        season.setSelection(seasonChoice);
        year.setSelection(yearChoice);

        saveClicks++;

        courseSubject = courseName.getText().toString();
        courseNumber = courseNum.getText().toString();
        quarterSpinnerChoice = seasonChoice;
        yearSpinnerChoice = yearChoice;


        Toast.makeText(AddAClassActivity.this,"Class Saved!", Toast.LENGTH_SHORT).show();

    }



    public void onGoBackAACPClicked(View view) {

        Intent intent = new Intent(this, UserClassListActivity.class);
        intent.putExtra("addNum",saveClicks);
        intent.putExtra("courseSubject",courseSubject);
        intent.putExtra("courseNumber",courseNumber);
        intent.putExtra("quarterSpinnerChoice",quarterSpinnerChoice);
        intent.putExtra("yearSpinnerChoice",yearSpinnerChoice);
        setResult(RESULT_OK, intent);

        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("addNum",saveClicks);



        startActivity(intent);
    }
}