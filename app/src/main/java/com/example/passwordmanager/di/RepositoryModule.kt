package com.example.passwordmanager.di

import com.example.passwordmanager.data.repository.LoginRepositoryImpl
import com.example.passwordmanager.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWallpaperRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}