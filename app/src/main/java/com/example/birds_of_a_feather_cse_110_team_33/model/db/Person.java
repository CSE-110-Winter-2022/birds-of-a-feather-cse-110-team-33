package com.example.birds_of_a_feather_cse_110_team_33.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// db for person
@Entity(tableName = "persons")
public class Person {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int personId;

    @ColumnInfo(name = "first_name")
    public String name;

    @ColumnInfo(name = "photo")
    public String photo;
}