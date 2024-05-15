package com.example.passwordmanager.presentation.mapper

import com.example.passwordmanager.data.local.Login
import com.example.passwordmanager.presentation.model.LoginUiInfo


fun Login.toLogUiInfo():LoginUiInfo{
    return LoginUiInfo(
        loginType=this.loginType,
        loginUsername=this.loginUsername,
        loginPassword=this.loginPassword
    )
}