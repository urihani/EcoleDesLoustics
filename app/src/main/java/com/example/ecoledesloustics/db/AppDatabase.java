package com.example.ecoledesloustics.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.ecoledesloustics.exercises_data.ExerciseDAO;
import com.example.ecoledesloustics.exercises_data.ExerciseDataModel;
import com.example.ecoledesloustics.scores.ScoresDAO;
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.users_data.UserDAO;
import com.example.ecoledesloustics.users_data.UserModel;

@Database(entities = {UserModel.class, ScoresTrackerModel.class, ExerciseDataModel.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDAO userDao();
    public abstract ScoresDAO scoresDAO();
    public abstract ExerciseDAO exerciseDAO();

}