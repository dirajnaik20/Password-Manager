package com.example.passwordmanager.domain.repository

import com.example.passwordmanager.data.local.Login
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun saveLogin(login: Login)

    suspend fun deleteLogin(login: Login)

    fun getSavedLogin(): Flow<List<Login>>

    suspend fun updateLogin(login: Login)

}