package com.example.birds_of_a_feather_cse_110_team_33;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    // NOT WORKING, NOT USED
    // converts string url to drawable to be displayed
    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
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
        private final ImageView personImageView;
        private final TextView sharedCoursesView;
        private Person person;

        ViewHolder(View itemView) {
            super(itemView);
            this.personNameView = itemView.findViewById(R.id.person_row_name);
            this.personImageView = itemView.findViewById(R.id.profile_pic);
            this.sharedCoursesView = itemView.findViewById(R.id.shared_courses);
            itemView.setOnClickListener(this);
        }

        public void setPerson(Person person) {
            this.person = person;
            this.personNameView.setText(person.getName());

            // NOT DONE!!!!
            // this.personImageView.setImageBitmap(getBitmapFromURL(person.getPhoto()));

            this.sharedCoursesView.setText("Shared Courses: " + person.getNumShared());
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
