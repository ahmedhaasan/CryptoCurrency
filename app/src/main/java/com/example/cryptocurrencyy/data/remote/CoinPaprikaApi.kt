package com.example.cryptocurrencyy.data.remote

import com.example.cryptocurrencyy.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyy.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * \    this retrofit api interface used to make http request to the server
 */
interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins():List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId")coinId:String):CoinDetailDto

}