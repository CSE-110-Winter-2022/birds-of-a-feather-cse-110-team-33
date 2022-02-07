package com.example.birds_of_a_feather_cse_110_team_33.model;

import com.example.birds_of_a_feather_cse_110_team_33.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.birds_of_a_feather_cse_110_team_33.MainActivity;

import java.util.ArrayList;

public class UserClassList extends AppCompatActivity {

    private LinearLayout parentLinearLayout;

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    public int howManyCreatedThusFar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_class_list);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);

        howManyCreatedThusFar = getIntent().getIntExtra("addNum",0);

        if (getIntent().getIntExtra("addNum",0) == 0) {
            howManyCreatedThusFar = preferences.getInt("addNum",0);
        }




        loadNewClasses();

    }

    public void loadNewClasses() {

        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);



        for (int i = 1; i < howManyCreatedThusFar; i++) {

            addRow();

        }

        //Populate the rows

        for (int i = 0; i < howManyCreatedThusFar; i++) {
            LinearLayout currentEntry = (LinearLayout) parentLinearLayout.getChildAt(i);
            TextView classFound = (TextView) currentEntry.getChildAt(0);
            String classFoundText = preferences.getString("classRow" + i,"");
            classFound.setText(classFoundText);

        }


    }

    public void addRow() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.classrow, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount()-2);

    }

    public void onDelete(View v) {

        parentLinearLayout.removeView((View) v.getParent());

        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        LinearLayout parentOfDeleteButton = (LinearLayout) v.getParent();
        int indexToRemoveFromSP = parentOfDeleteButton.indexOfChild(v);



        howManyCreatedThusFar--;

    }

    public void onAddAClassClicked(View view) {
        Intent intent = new Intent(this,AddAClassPage.class);
        //Send how many were created so we know where to start from.

        intent.putExtra("addNumB",howManyCreatedThusFar);
        startActivity(intent);
    }

    public void onGoBackClickedLD(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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