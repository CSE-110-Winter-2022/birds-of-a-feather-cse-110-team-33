package com.example.birds_of_a_feather_cse_110_team_33.filtering;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.List;

// Strategy Pattern: interface for different filters (recency, current, total, size)
public interface IFilter {
    void filter(List<Person> persons);
}
