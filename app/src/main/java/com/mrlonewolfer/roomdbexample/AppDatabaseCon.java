package com.mrlonewolfer.roomdbexample;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserBean.class},version = 1,exportSchema = false)
public abstract class AppDatabaseCon extends RoomDatabase {


    public abstract UserDao userDao();
}
