package com.example.cryptocurrencyy.presentation.coin_details

import com.example.cryptocurrencyy.domain.model.Coin
import com.example.cryptocurrencyy.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading:Boolean = false,
    val coin : CoinDetail? = null,
    val error:String = ""
)
