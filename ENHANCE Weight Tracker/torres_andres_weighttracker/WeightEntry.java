package com.example.torres_andres_weighttracker;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity // Marks this class as a Room Entity (i.e., a table in the database)
public class WeightEntry {

    @PrimaryKey(autoGenerate = true) // This field will be the primary key and will auto-increment
    public int id;

    @NonNull // This field cannot be null
    public String date; // The date the weight was recorded

    public float weight; // The recorded weight in pounds

    // Constructor to create a new WeightEntry with a date and weight
    public WeightEntry(@NonNull String date, float weight) {
        this.date = date;
        this.weight = weight;
    }
}