package com.example.cryptocurrencyy.presentation.coin_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyy.common.Resource
import com.example.cryptocurrencyy.domain.use_case.get_coins.GetCoinsUseCase
import com.example.cryptocurrencyy.domain.use_case.get_single_coin.GetSingleCoinUseCase
import com.example.cryptocurrencyy.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 *  this is the view model but we will use the use case instead of the repository
 *  the use for viewModel now is to maintain the state only
 *  as we moved alot of the business logic to the usecases in domain layer
 *  and as we know the viewModel keep the state in different configurations of the screen
 */
@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getSingleCoinUseCase: GetSingleCoinUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CoinDetailState())
    val state = _state.asStateFlow()

    private fun getCoinDetail(coinId: String) {
        getSingleCoinUseCase.invoke(coinId)
            // or
            // getCoinsUseCase
            .onEach { result ->
                when (result) {
                    is Resource.Sucsses -> {
                        _state.value =
                            CoinDetailState(coin = result.data)
                    }

                    is Resource.Error -> {
                        _state.value =
                            CoinDetailState(error = result.message ?: "An unexpected error occured")
                    }

                    is Resource.Loading -> {
                        _state.value = CoinDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }
}