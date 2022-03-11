package com.example.birds_of_a_feather_cse_110_team_33.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.birds_of_a_feather_cse_110_team_33.MainActivity;

import java.util.List;

// db for person
@Entity(tableName = "persons")
public class Person {
    @PrimaryKey
    @ColumnInfo(name = "person_id")
    public int personId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "photo")
    public String photo;

    @ColumnInfo(name = "num_shared")
    public int num_shared;


    @ColumnInfo(name = "favorite")
    public boolean isFavorite;

    @ColumnInfo(name = "current_shared")
    public int current_shared;

    @ColumnInfo(name = "size_value")
    public double size_value;

    @ColumnInfo(name = "recency_value")
    public double recency_value;

    public Person(String name, String photo) {
        this.name = name;
        this.photo = photo;
        this.size_value = 0;
        this.recency_value = 0;
    }

    public int getPersonId() { return personId; }
    public void setPersonId(int personId) { this.personId = personId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
    public int getNumShared() { return num_shared; }
    public void setNumShared(int num_shared) { this.num_shared = num_shared; }

    public boolean getFavorite() { return isFavorite; }
    public void setFavorite() { this.isFavorite = true; }
    public void setNFavorite() { this.isFavorite = false; }

    public int getCurrentShared() { return current_shared; }
    public void setCurrentShared(int num_shared) { this.current_shared = num_shared; }
    public double getSizeValue() { return this.size_value; }
    public void setSizeValue(double size_value) { this.size_value = size_value; }
    public double getRecencyValue() { return this.recency_value; }
    public void setRecencyValue(double recency_value) { this.recency_value = recency_value; }
}