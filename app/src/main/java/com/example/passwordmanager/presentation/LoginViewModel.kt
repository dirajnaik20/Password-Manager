package com.example.passwordmanager.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.local.Login
import com.example.passwordmanager.domain.repository.LoginRepository
import com.example.passwordmanager.utils.EncryptionUtils
import com.example.passwordmanager.utils.Password
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.crypto.SecretKey
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val loginRepository: LoginRepository

) : ViewModel() {

    private var _savedLoginList: LiveData<List<Login>> =
        loginRepository.getSavedLogin().asLiveData()
    val savedLoginList = _savedLoginList

    private var viewModelKey= mutableStateOf("")
    private var viewModelPassword= mutableStateOf("")

    fun saveLogin(login: Login) {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.saveLogin(login)
        }

    }

    fun updateLogin(login: Login) {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.updateLogin(login)
        }

    }

    fun deleteLogin(login: Login) {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.deleteLogin(login)
        }

    }

    fun encryptThePassword(password:String):Password{
        val key:SecretKey=EncryptionUtils.generateAESKey()
        viewModelPassword.value=EncryptionUtils.encryptPassword(password,key)
        return Password(
            key=key,
            password=viewModelPassword.value
        )

    }

    fun decryptThePassword(password: Password): String {
        return EncryptionUtils.decryptPassword(password.password, password.key)
    }


}