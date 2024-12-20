package com.example.cryptocurrencyy.presentation.coin_list

import com.example.cryptocurrencyy.domain.model.Coin

data class CoinListState(
    val isLoading:Boolean = false,
    val coins:List<Coin> = emptyList(),
    val error:String = ""
)
