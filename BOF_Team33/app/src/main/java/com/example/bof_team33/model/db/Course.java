package com.example.bof_team33.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// db for courses
@Entity(tableName = "course")
public class Course {
    // add a course
    // should store the info (year, quarter, subject, course #)
    public Course(int courseId, int personId, int year, String quarter, String subject, String course_num) {
        this.courseId = courseId;
        this.personId = personId;
        this.year = year;
        this.quarter = quarter;
        this.subject = subject;
        this.course_num = course_num;
    }

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int courseId;

    @ColumnInfo(name = "person_id")
    public int personId;

    @ColumnInfo(name = "year")
    public int year;

    @ColumnInfo(name = "quarter")
    public String quarter;

    @ColumnInfo(name = "subject")
    public String subject;

    @ColumnInfo(name = "course_num")
    public String course_num;
}
