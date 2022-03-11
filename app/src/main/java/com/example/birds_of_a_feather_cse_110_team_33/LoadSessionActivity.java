package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class LoadSessionActivity extends AppCompatActivity {

    int sessionCount;

    ArrayList<String> savedSessions =new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_session);

        sessionCount = getIntent().getIntExtra("sessionCount",0);

        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        //Add the sessionNames to an array list
        for (int i = 1; i < sessionCount+1; i++) {
            String session = preferences.getString("sessionTitle"+i,"default" + i);
            savedSessions.add(session);
        }


        //Dynamically add these to the list of choices in the drop down menu.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,savedSessions);

        Spinner savedSessionSpnr = (Spinner) findViewById(R.id.session_list_spinner);
        savedSessionSpnr.setAdapter(adapter);



    }

    public void confirmLoadSession(View view) {


        Spinner savedSessionSpnr = (Spinner) findViewById(R.id.session_list_spinner);

        int sessionChoice = savedSessionSpnr.getSelectedItemPosition();

        //Choice starts indexing from 0, need to add 1 to get the correct session count.
        sessionChoice++;

        //Based on the choice made, load that session
        SharedPreferences preferences = getSharedPreferences("pref one",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("approvalToLoad",true);
        editor.putInt("sessionToLoad",sessionChoice);

        editor.commit();


        String sessName = preferences.getString("sessionTitle"+sessionChoice,"");
        //Intent intent = new Intent(this, HomePageActivity.class);

        Toast.makeText(LoadSessionActivity.this,"Session: " + sessName + " Loading...", Toast.LENGTH_LONG).show();


        finish();
        //startActivity(intent);


    }
}