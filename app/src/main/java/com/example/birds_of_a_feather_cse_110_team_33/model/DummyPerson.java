package com.example.birds_of_a_feather_cse_110_team_33.model;

import androidx.room.ColumnInfo;

import java.util.Arrays;
import java.util.List;

// use this class for base testing
// if the database isn't working -_-
public class DummyPerson {
    public int personId;
    public String name;
    public String photo;

    public DummyPerson(int personId, String name, String photo) {
        this.personId = personId;
        this.name = name;
        this.photo = photo;
    }

    public DummyPerson(String name) { this.name = name; }
    public int getDummyPersonId() { return personId; }
    public void setDummyPersonId(int personId) { this.personId = personId; }
    public String getDummyName() { return name; }
    public void setDummyName(String name) { this.name = name; }
    public String getDummyPhoto() { return photo; }
    public void setDummyPhoto(String photo) { this.photo = photo; }
}
