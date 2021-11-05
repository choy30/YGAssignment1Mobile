package com.example.mobiletest3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registUser(UserEntity userEntity);

    @Query("SELECT * from user where email=(:email) and password=(:password)")
    UserEntity login(String email, String password);

}
