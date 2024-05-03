package com.example.passwordmanager.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDAO {

    @Insert
    suspend fun saveLogin(login: Login)
    @Delete
    suspend fun deleteLogin(login: Login)

    @Query("SELECT * FROM login_data_table")
    fun getAllLogin(): Flow<List<Login>>
    @Update
    suspend fun updateLogin(login: Login)

}