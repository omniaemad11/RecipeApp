package com.example.receta.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Insert
import com.example.receta.database.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users where email = :email")
    suspend fun getUserByEmail(email: String): User

    @Delete
    fun deleteUser(user: User)

}