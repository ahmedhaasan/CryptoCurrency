package com.example.cryptocurrencyy.domain.model

/**
 *      this is the lighter weight version of the coin dto class
 *      that actually will use to represent the data on the ui
 */
data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)