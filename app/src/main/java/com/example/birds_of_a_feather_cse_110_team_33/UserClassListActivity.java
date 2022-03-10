package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.CoursesDao;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

import java.util.List;

public class UserClassListActivity extends AppCompatActivity {

    private LinearLayout parentLinearLayout;

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    public int howManyCreatedThusFar;
    public int userId;
    public String courseSubject;
    public String courseNumber;
    public int quarterSpinnerChoice;
    public int yearSpinnerChoice;
    public int classSizeSpinnerChoice;
    public CoursesDao coursesDao;
    public PersonDao personDao;
    protected AppDatabase db;
    public List<Course> listCourse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_class_list);

        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        howManyCreatedThusFar = getIntent().getIntExtra("addNum",0);

        userId = getIntent().getIntExtra("user",0);
        setTitle("Your Current Added Classes");

        db = AppDatabase.singleton(this);
        coursesDao = db.coursesDao();
        personDao = db.personDao();

        if (howManyCreatedThusFar != 0) {
            listCourse = coursesDao.getForPerson(userId);
        }

        courseSubject = getIntent().getStringExtra("courseSubject");
        courseNumber = getIntent().getStringExtra("courseNumber");
        quarterSpinnerChoice = getIntent().getIntExtra("quarterSpinnerChoice",0);
        yearSpinnerChoice = getIntent().getIntExtra("yearSpinnerChoice",0);
        classSizeSpinnerChoice = getIntent().getIntExtra("classSizeSpinnerChoice",0);


        loadNewClasses();

    }

    public void loadNewClasses() {

        /*
        LinearLayout firstEntry = (LinearLayout) parentLinearLayout.getChildAt(0);
        TextView firstTextBox = (TextView) firstEntry.getChildAt(0);
        if (firstTextBox.getText().toString().equals("Example")) {
            parentLinearLayout.removeView((View) firstTextBox.getParent());
        }
        */


        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        for (int i = 1; i < howManyCreatedThusFar; i++) {

            addRow();

        }

        //Populate the rows

        for (int i = 0; i < howManyCreatedThusFar; i++) {
            LinearLayout currentEntry = (LinearLayout) parentLinearLayout.getChildAt(i);
            TextView classFound = (TextView) currentEntry.getChildAt(0);
            String classFoundTextDB = listCourse.get(i).toString();
            String classFoundText = preferences.getString("classRow" + i,"");
            classFound.setText(classFoundTextDB);

        }


    }

    public void addRow() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.classrow, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount()-3);

    }

    public void onDelete(View v) {

        parentLinearLayout.removeView((View) v.getParent());


        LinearLayout parentOfDeleteButton = (LinearLayout) v.getParent();
        TextView textBox = (TextView) parentOfDeleteButton.getChildAt(0);
        String textBoxText = textBox.getText().toString();

        // Need to delete from DB
        List<Course> userCourses = coursesDao.getForPerson(userId);

        for (Course course: userCourses) {
            String currentCourseString = course.toString();
            if (textBoxText.equals(currentCourseString)) {
                coursesDao.delete(course);
            }
        }

        howManyCreatedThusFar--;

    }

    public void onAddAClassClicked(View view) {
        Intent intent = new Intent(this, AddAClassActivity.class);
        //Send how many were created so we know where to start from.

        intent.putExtra("addNumB",howManyCreatedThusFar);
        intent.putExtra("user",userId);
        intent.putExtra("courseSubject",courseSubject);
        intent.putExtra("courseNumber",courseNumber);
        intent.putExtra("quarterSpinnerChoice",quarterSpinnerChoice);
        intent.putExtra("yearSpinnerChoice",yearSpinnerChoice);
        intent.putExtra("classSizeSpinnerChoice",classSizeSpinnerChoice);
        startActivity(intent);
    }

    public void onFinishedClicked(View view) {
        Context context = view.getContext();
        Intent intent  = new Intent(context, HomePageActivity.class);
        intent.putExtra("user", userId);
        intent.putExtra("initialCount",0);
        context.startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // get String data from Intent
                howManyCreatedThusFar = data.getIntExtra("addNum",0);


            }
        }
    }
}