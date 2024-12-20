package com.example.cryptocurrencyy.domain.use_case.get_coins

import com.example.cryptocurrencyy.common.Resource
import com.example.cryptocurrencyy.data.remote.dto.toCoin
import com.example.cryptocurrencyy.domain.model.Coin
import com.example.cryptocurrencyy.domain.repository.ICoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

/**
 *      the use case should have only one public function
 *      which is one major feature
 */
class GetCoinsUseCase @Inject constructor(
    private val repository: ICoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() } // map each coinDto into Coin
            emit(Resource.Sucsses(coins))
        } catch (e: HttpException) {

            emit(Resource.Error(e.localizedMessage ?: " Un Expected Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))

        }

    }
}