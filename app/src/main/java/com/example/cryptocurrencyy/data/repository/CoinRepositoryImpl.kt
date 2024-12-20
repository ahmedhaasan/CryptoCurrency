package com.example.cryptocurrencyy.data.repository

import android.util.Log
import com.example.cryptocurrencyy.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyy.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyy.data.remote.dto.CoinDto
import com.example.cryptocurrencyy.domain.repository.ICoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : ICoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        Log.d("CoinRepositoryImpl", "${api.getCoins()}")
       return api.getCoins()
    }   

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}