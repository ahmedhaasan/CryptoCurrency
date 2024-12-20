package com.example.cryptocurrencyy.presentation.coin_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyy.common.Resource
import com.example.cryptocurrencyy.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
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
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel(){
    private  val _state = MutableStateFlow(CoinListState())
    val state = _state.asStateFlow()

    init{
        getCoins()  // when the view model is created get the coins automatically
    }

    private fun getCoins(){
        getCoinsUseCase.invoke()
        // or
       // getCoinsUseCase
            .onEach { result ->
                when (result) {
                    is Resource.Sucsses -> {_state.value = CoinListState(coins = result.data ?: emptyList())}
                    is Resource.Error -> {_state.value = CoinListState(error = result.message ?: "An unexpected error occured")}
                    is Resource.Loading ->{ _state.value = CoinListState(isLoading = true)}
                }
            }.launchIn(viewModelScope)
    }
}