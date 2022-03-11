package com.example.birds_of_a_feather_cse_110_team_33.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// db for courses
@Entity(tableName = "courses")
public class Course {
    // add a course
    // should store the info (year, quarter, subject, course #)
    public Course(int personId, int year, String quarter, String subject, String course_num,String course_size) {
        this.personId = personId;
        this.year = year;
        this.quarter = quarter;
        this.subject = subject;
        this.course_num = course_num;
        this.course_size = course_size;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private int courseId = 0;

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

    @ColumnInfo(name = "course_size")
    public String course_size;

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public int getPersonId() { return personId; }
    public void setPersonId(int personId) { this.personId = personId; }
    public String getCourseSize() { return course_size; }
    public String getQtr() { return this.quarter; }
    public int getYear() { return this.year; }
    public void setCourseSize(String course_size) { this.course_size = course_size; }
    public String toString() {
        return subject + " " + course_num + " " + quarter + " " + year + " " + course_size;
    }
}
