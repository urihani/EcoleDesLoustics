package com.example.ecoledesloustics.users_data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ecoledesloustics.users_data.UserModel;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<UserModel> getAll();

    @Query("SELECT * FROM user WHERE first_name = :firstName AND last_name = :lastName")
    UserModel getUserFromName(String firstName, String lastName);

    @Query("SELECT * FROM user WHERE id = :id")
    UserModel getUserFromId(long id);

    @Insert
    long insert(UserModel user);

    @Insert
    long[] insertAll(UserModel... users);

    @Delete
    void delete(UserModel user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(UserModel user);
}
