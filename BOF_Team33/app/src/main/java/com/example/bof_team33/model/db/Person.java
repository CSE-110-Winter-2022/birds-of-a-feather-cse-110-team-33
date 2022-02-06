package com.example.bof_team33.model.db;

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

