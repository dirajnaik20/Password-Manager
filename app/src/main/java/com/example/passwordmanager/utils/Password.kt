package com.example.passwordmanager.utils

import javax.crypto.SecretKey

data class Password(
    val key:SecretKey,
    val password:String
)
