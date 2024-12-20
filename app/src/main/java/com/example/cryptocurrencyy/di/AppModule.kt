package com.example.cryptocurrencyy.di

import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrencyy.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyy.data.repository.CoinRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // means it will live as long as the application does
object AppModule {

    // create the functions that will provide the dependencies
    @Provides
    @Singleton  // provides only single instance
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java) // now created
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api:CoinPaprikaApi):CoinRepositoryImpl{
        return CoinRepositoryImpl(api)
    }


}