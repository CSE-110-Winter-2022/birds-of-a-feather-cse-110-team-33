package com.example.birds_of_a_feather_cse_110_team_33;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.Course;

import java.util.List;
import java.util.function.Consumer;

public class ProfileViewAdapter extends RecyclerView.Adapter<ProfileViewAdapter.ViewHolder> {
    private final List<Course> courses;

    public ProfileViewAdapter(List<Course> courses) {
        super();
        this.courses = courses;
    }

    @NonNull
    @Override
    public ProfileViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.course_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewAdapter.ViewHolder holder, int position) {
        holder.setCourse(courses.get(position));
    }

    @Override
    public int getItemCount() {
        return this.courses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView subjectTextView;
        private final TextView courseNumTextView;
        private final TextView qtrTextView;
        private final TextView yearTextView;
        private Course course;

        ViewHolder(View itemView) {
            super(itemView);
            this.subjectTextView = itemView.findViewById(R.id.subject);
            this.courseNumTextView = itemView.findViewById(R.id.course_num);
            this.qtrTextView = itemView.findViewById(R.id.qtr);
            this.yearTextView = itemView.findViewById(R.id.year);
        }

        public void setCourse(Course course) {
            this.course = course;
            this.subjectTextView.setText(course.getSubject());
            this.courseNumTextView.setText(course.getCourseNum());
            this.qtrTextView.setText(course.getQuarter());
            this.yearTextView.setText(course.getYear());
        }
    }


}
