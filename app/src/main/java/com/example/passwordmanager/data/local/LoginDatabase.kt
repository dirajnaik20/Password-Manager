package com.example.passwordmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Login::class],version = 1)
abstract class LoginDatabase : RoomDatabase() {
    abstract fun loginDAO(): LoginDAO

}
