package com.example.cryptocurrencyy.di

import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrencyy.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyy.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencyy.domain.repository.ICoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // means it will live as long as the application does
object AppModule {

    // create the functions that will provide the dependencies

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // create retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // create the api instance
    @Provides
    @Singleton  // provides only single instance
    fun providePaprikaApi(retrofit: Retrofit): CoinPaprikaApi {

        return retrofit.create(CoinPaprikaApi::class.java) // now created
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): ICoinRepository {
        return CoinRepositoryImpl(api)
    }


}