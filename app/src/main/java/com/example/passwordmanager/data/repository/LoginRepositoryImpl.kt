package com.example.passwordmanager.data.repository

import com.example.passwordmanager.data.local.Login
import com.example.passwordmanager.data.local.LoginDAO
import com.example.passwordmanager.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val dao: LoginDAO
):LoginRepository {
    override suspend fun saveLogin(login: Login) {
        dao.saveLogin(login)
    }

    override suspend fun deleteLogin(login: Login) {
        dao.deleteLogin(login)
    }

    override fun getSavedLogin(): Flow<List<Login>> {
        return dao.getAllLogin()
    }

    override suspend fun updateLogin(login: Login) {
        dao.updateLogin(login)
    }
}