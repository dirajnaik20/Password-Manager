package com.example.passwordmanager.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.local.Login
import com.example.passwordmanager.domain.repository.LoginRepository
import com.example.passwordmanager.presentation.model.LoginUiInfo
import com.example.passwordmanager.utils.EncryptionUtils
import com.example.passwordmanager.utils.Password
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val loginRepository: LoginRepository

) : ViewModel() {

    private var _allLoginDetail: Flow<List<Login>> = loginRepository.getSavedLogin()
    val allLoginDetails = _allLoginDetail

    private var _loginType = mutableStateOf("")
    val loginType = _loginType

    private var _loginUsername = mutableStateOf("")
    val loginUsername = _loginUsername

    private var _loginPassword = mutableStateOf("")
    val loginPassword = _loginPassword

    fun updateLoginType(loginType: String) {
        _loginType.value = loginType
    }

    fun updateLoginUsername(loginUsername: String) {
        _loginUsername.value = loginUsername
    }

    fun updateLoginPassword(loginPassword: String) {
        _loginPassword.value = loginPassword
    }
    fun updateDecryptedLoginPasswordToEdit(password: Password) {
        _loginPassword.value = decryptThePassword(password)
    }

    fun saveLoginDetails(loginUiInfo: LoginUiInfo): Boolean {

        Log.d("Diraj","In viewmodel saveLoginDetailsDiraj")

        if (
            loginUiInfo.loginType.isNotEmpty() &&
            loginUiInfo.loginUsername.isNotEmpty() &&
            loginUiInfo.loginPassword.isNotEmpty()

        ) {

            Log.d("Diraj","In viewmodel saveLoginDetails if ")

            val password = encryptThePassword(loginUiInfo.loginPassword)
            viewModelScope.launch(Dispatchers.IO) {
                loginRepository.saveLogin(
                    Login(
                        id = 0,
                        loginType = loginUiInfo.loginType,
                        loginUsername = loginUiInfo.loginUsername,
                        loginPassword = password.password,
                        loginKey = password.key
                    )
                )
            }

            resetLoginFields()
            return true


        }

        return false

    }

    fun deleteLoginDetails(login: Login) {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.deleteLogin(login)
        }
        resetLoginFields()
    }

    private fun encryptThePassword(password: String): Password {
        val secreteKey = EncryptionUtils.generateAESKey()
        val encryptedPassword = EncryptionUtils.encryptPassword(password, secreteKey)
        return Password(secreteKey, encryptedPassword)

    }

    fun decryptThePassword(password: Password): String {
        return EncryptionUtils.decryptPassword(password.password, password.key)

    }

    fun resetLoginFields() {
        _loginType.value = ""
        _loginUsername.value = ""
        _loginPassword.value = ""
    }

    fun updateLoginDetails(login: Login, loginUiInfo: LoginUiInfo): Boolean {
        if (loginUiInfo.loginType.isNotEmpty() &&
            loginUiInfo.loginUsername.isNotEmpty() &&
            loginUiInfo.loginPassword.isNotEmpty()
        ) {

            val notEditedPassword =
                decryptThePassword(Password(login.loginKey, login.loginPassword))

            if (loginUiInfo.loginPassword != notEditedPassword) {
                val editedPasswordEncryption = encryptThePassword(loginUiInfo.loginPassword)

                viewModelScope.launch(Dispatchers.IO) {
                    loginRepository.updateLogin(
                        Login(
                            id = login.id,
                            loginType = loginUiInfo.loginType,
                            loginUsername = loginUiInfo.loginUsername,
                            loginPassword = editedPasswordEncryption.password,
                            loginKey = editedPasswordEncryption.key
                        )
                    )
                }
                return true

            } else {

                viewModelScope.launch(Dispatchers.IO) {
                    loginRepository.updateLogin(
                        Login(
                            id = login.id,
                            loginType = loginUiInfo.loginType,
                            loginUsername = loginUiInfo.loginUsername,
                            loginPassword = login.loginPassword,
                            loginKey = login.loginKey
                        )
                    )


                }

                return true

            }

        }
        return false

    }


}