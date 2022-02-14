package com.example.birds_of_a_feather_cse_110_team_33;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.PersonDao;

public class ConfirmNameActivity extends AppCompatActivity {
    private PersonDao personDao;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_name);
        setTitle("Confirm Name");

        db = AppDatabase.singleton(this);
        personDao = db.personDao();

        // Retrieving name associated with Google Account
        AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] list = manager.getAccounts();
        String name;

        for (Account account : list)
        {
            if (account.type.equalsIgnoreCase("com.google"))
            {
                name = account.name;
                break;
            }
        }
        name = "Ethan";

        TextView nameView = findViewById(R.id.user_name);
        nameView.setText(name);
    }

    public void onConfirmClicked(View view) {
        TextView viewName = findViewById(R.id.user_name);
        String name = viewName.getText().toString();
        Person user = new Person(name, null);
        user.setPersonId(personDao.maxId() + 1);
        personDao.insert(user);

        Context context = view.getContext();
        Intent intent  = new Intent(context, UserClassListActivity.class);
        intent.putExtra("user", user.getPersonId());
        context.startActivity(intent);
    }
}