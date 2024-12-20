package com.example.cryptocurrencyy.domain.repository

import com.example.cryptocurrencyy.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyy.data.remote.dto.CoinDto
import com.example.cryptocurrencyy.domain.model.Coin

/**
 *      in this part which is business logic we define only the interface of the reposiatory
 */
interface ICoinRepository {
    suspend fun  getCoins():List<CoinDto>
    suspend fun getCoinById(coinId:String):CoinDetailDto
}