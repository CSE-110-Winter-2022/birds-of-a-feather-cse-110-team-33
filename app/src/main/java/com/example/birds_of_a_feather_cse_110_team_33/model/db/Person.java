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

    @ColumnInfo(name = "waved")
    public boolean hasWaved;

    public Person(String name, String photo) {
        this.name = name;
        this.photo = photo;
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
    public void setWaved() { this.hasWaved = true; }
    public void setNWaved() { this.hasWaved = false; }
}