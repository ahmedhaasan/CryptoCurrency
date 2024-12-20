package com.example.cryptocurrencyy.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyy.presentation.components.Screens.CoinDetailScreen
import com.example.cryptocurrencyy.presentation.coin_details.CoinDetailScreen
import com.example.cryptocurrencyy.presentation.coin_list.CoinListScreen

sealed class Screens(val route: String) {
    object CoinListScreen : Screens("coin_list_screen")
    object CoinDetailScreen : Screens("coin_detail_screen/{coinId}")

}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.CoinListScreen.route) {
        composable(route = Screens.CoinListScreen.route) {
            CoinListScreen(navController)
        }

        composable(route = CoinDetailScreen.route +"/{coinId}") {
            CoinDetailScreen()
        }
    }
}
