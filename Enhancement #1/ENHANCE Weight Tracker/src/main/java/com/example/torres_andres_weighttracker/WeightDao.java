package com.example.torres_andres_weighttracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao // Marks this interface as a Data Access Object for Room
public interface WeightDao {

    @Insert // Inserts a new weight entry into the database
    void insert(WeightEntry weightEntry);

    @Update // Updates an existing weight entry
    void update(WeightEntry weightEntry);

    @Delete // Deletes a specific weight entry
    void delete(WeightEntry weightEntry);

    @Query("SELECT * FROM WeightEntry ORDER BY date DESC") // Retrieves all entries ordered by date descending
    List<WeightEntry> getAll();

    @Query("DELETE FROM WeightEntry") // Deletes all entries in the table
    void deleteAll();

    @Query("SELECT * FROM WeightEntry ORDER BY id DESC LIMIT 1") // Retrieves the most recently added entry
    WeightEntry getMostRecent();
}