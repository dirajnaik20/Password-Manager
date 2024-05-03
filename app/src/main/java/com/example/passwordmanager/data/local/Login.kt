package com.example.passwordmanager.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


@Entity(tableName = "login_data_table")
@TypeConverters(SecretKeyConverter::class)
data class Login(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="login_id")
    val id: Int,
    @ColumnInfo(name="login_type")
    val loginType: String,
    @ColumnInfo(name="login_username")
    val loginUsername: String,
    @ColumnInfo(name="login_password")
    val loginPassword: String,
    @ColumnInfo(name="login_key")
    val loginKey: SecretKey, // Store SecretKey as ByteArray

)
class SecretKeyConverter {

    @TypeConverter
    fun toSecretKey(byteArray: ByteArray): SecretKey {
        return SecretKeySpec(byteArray, "AES")
    }

    @TypeConverter
    fun fromSecretKey(secretKey: SecretKey): ByteArray {
        return secretKey.encoded
    }
}