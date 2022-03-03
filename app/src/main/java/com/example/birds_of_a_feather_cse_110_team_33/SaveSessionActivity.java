package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.birds_of_a_feather_cse_110_team_33.HomePageActivity;
import com.example.birds_of_a_feather_cse_110_team_33.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SaveSessionActivity extends AppCompatActivity {
    
    private int currentSessionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_session);


        currentSessionCount = getIntent().getIntExtra("sessionCount",0);



    }

    public void onSaveSessionAndNameClicked(View view) {
        //Save title in shared preferences, called "sessionTitle" + sessionNumber
        EditText sessionName = (EditText) findViewById(R.id.save_session_title);
        String sessionNameString = sessionName.getText().toString();

        if (sessionNameString.equals("")) {
            Date date = Calendar. getInstance(). getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat. format(date);

            sessionNameString = strDate;

        }

        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        
        editor.putString("sessionTitle"+currentSessionCount,sessionNameString);

        editor.commit();


        Intent intent = new Intent(this, HomePageActivity.class);

        Toast.makeText(SaveSessionActivity.this,"Session Saved As: " + sessionNameString, Toast.LENGTH_SHORT).show();


        //Let message display, delay to let user see it, then we are good to goto next activity.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // yourMethod();
            }
        }, 3000);



        startActivity(intent);
        
    }
}