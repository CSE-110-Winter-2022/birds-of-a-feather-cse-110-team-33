package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home Page Testing");

        // AppDatabase db = AppDatabase.singleton(getApplicationContext());
    }

    public void onStartClicked(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, HomePageActivity.class);
        context.startActivity(intent);
    }
}
