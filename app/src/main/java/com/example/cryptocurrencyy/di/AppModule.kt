package com.example.cryptocurrencyy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // means it will live as long as the application does
object AppModule {

    // create the functions that will provide the dependencies
    @Provides
    @Singleton  // provides only single instance thrwo the intier app


}