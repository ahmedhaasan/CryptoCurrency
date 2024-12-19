package com.example.cryptocurrencyy.data.remote.dto

import com.example.cryptocurrencyy.domain.model.Coin

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

// fun to convert from Coin Dto to Coin
fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )
}