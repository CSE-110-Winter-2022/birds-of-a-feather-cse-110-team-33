package com.example.birds_of_a_feather_cse_110_team_33.filtering_sorting;

import com.example.birds_of_a_feather_cse_110_team_33.model.db.Person;

import java.util.List;

// Strategy Pattern: interface for different sorts (recency, size)
public interface ISorter {
    void sort(List<Person> persons);
}
