package com.example.receta.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            if (instance != null) return instance as UserDatabase

            instance =
                Room.databaseBuilder(context, UserDatabase::class.java, "users_database").build()
            return instance as UserDatabase
        }
    }
}