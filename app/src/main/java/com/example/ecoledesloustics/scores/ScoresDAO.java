package com.example.ecoledesloustics.scores;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ecoledesloustics.users_data.UserModel;

import java.util.List;
import androidx.room.Dao;
        import androidx.room.Delete;
        import androidx.room.Insert;
        import androidx.room.OnConflictStrategy;
        import androidx.room.Query;
        import androidx.room.Update;

        import com.example.ecoledesloustics.users_data.UserModel;

        import java.util.List;

@Dao
public interface ScoresDAO {
    @Query("SELECT * FROM scores")
    List<ScoresTrackerModel> getAll();

    @Query("SELECT * FROM scores WHERE user_id = :userId")
    ScoresTrackerModel getScoreFromUserId(Long userId);

    @Insert
    long insert(ScoresTrackerModel score);

    @Insert
    long[] insertAll(ScoresTrackerModel... scores);

    @Delete
    void delete(ScoresTrackerModel score);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ScoresTrackerModel score);
}

