package com.example.ecoledesloustics.exercises_data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExerciseDAO {
    @Query("SELECT * FROM exercise")
    List<ExerciseDataModel> getAll();

    @Query("SELECT * FROM exercise WHERE id = :id")
    ExerciseDataModel getExerciseFromId(long id);

    @Insert
    long insert(ExerciseDataModel exercise);

    @Insert
    long[] insertAll(ExerciseDataModel... exercises);

    @Delete
    void delete(ExerciseDataModel exercise);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ExerciseDataModel exercise);
}