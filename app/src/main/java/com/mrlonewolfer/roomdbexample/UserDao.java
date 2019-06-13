package com.mrlonewolfer.roomdbexample;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Insert
    void saveUser(UserBean userBean);

    @Update
    void updateUser(UserBean userBean);

    @Delete
    void removeUser(UserBean userBean);

    @Query("Select * From userbean")
    List<UserBean> getAllUserBeans();
}
