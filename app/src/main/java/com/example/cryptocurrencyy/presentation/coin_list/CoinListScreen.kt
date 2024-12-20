package com.example.cryptocurrencyy.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrencyy.presentation.coin_list.components.CoinListItem
import com.example.cryptocurrencyy.presentation.coin_list.components.EnhancedCoinListItem
import com.example.cryptocurrencyy.presentation.components.Screens
import com.example.cryptocurrencyy.presentation.coin_list.components.ErrorScreen
import com.example.cryptocurrencyy.presentation.coin_list.components.LoadingScreen

@Composable

fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value// collect the viewMOdel state
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color.LightGray)
                .padding(innerPadding)
        ) {
            // case Resource is success
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.coins) { coin ->
                    EnhancedCoinListItem(coin = coin)
                    {
                        // navigation here
                        navController.navigate(Screens.CoinDetailScreen.route + "/${coin.id}")
                    }
                }
            }

            // case Resource is loading
            if (state.isLoading) {
                LoadingScreen()
            }
            // case the Resource or the state is error
            if (state.error.isNotBlank()) {
                ErrorScreen(errorMessage = state.error)
            }

        }
    }
}


