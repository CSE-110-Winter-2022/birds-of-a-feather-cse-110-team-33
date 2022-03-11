package com.example.birds_of_a_feather_cse_110_team_33;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.AppDatabase;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;
import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PersonsViewAdapter extends RecyclerView.Adapter<PersonsViewAdapter.ViewHolder> {
    private final List<Person> persons;
    private static int userId;

    public PersonsViewAdapter(List<Person> persons, int userId) {
        super();
        this.persons = persons;
        this.userId = userId;
    }

    @NonNull
    @Override
    public PersonsViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.person_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonsViewAdapter.ViewHolder holder, int position) {
        holder.setPerson(persons.get(position));
    }

    @Override
    public int getItemCount() { return this.persons.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView personNameView;
        private final TextView personImageView;
        private final TextView sharedCoursesView;
        private final TextView currentCoursesView;
        private Person person;

        ViewHolder(View itemView) {
            super(itemView);
            this.personNameView = itemView.findViewById(R.id.person_row_name);
            this.personImageView = itemView.findViewById(R.id.profile_pic);
            this.sharedCoursesView = itemView.findViewById(R.id.shared_courses);
            this.currentCoursesView = itemView.findViewById(R.id.current_courses);
            personImageView.setBackgroundDrawable(ContextCompat.getDrawable(personNameView.getContext(), R.drawable.default_pp));
            itemView.setOnClickListener(this);
        }

        public void setPerson(Person person) {
            this.person = person;
            this.personNameView.setText(person.getName());

            personImageView.setBackgroundDrawable(ContextCompat.getDrawable(personNameView.getContext(), R.drawable.default_pp));

            this.currentCoursesView.setText("Current: " + person.getCurrentShared());
            this.sharedCoursesView.setText("Total: " + person.getNumShared());
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, PersonDetailActivity.class);
            intent.putExtra("person_id", this.person.getPersonId());
            intent.putExtra("user", userId);
            context.startActivity(intent);
        }
    }
}
