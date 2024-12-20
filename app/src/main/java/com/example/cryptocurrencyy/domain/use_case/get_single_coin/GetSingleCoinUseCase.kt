package com.example.cryptocurrencyy.domain.use_case.get_single_coin

import com.example.cryptocurrencyy.common.Resource
import com.example.cryptocurrencyy.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyy.data.remote.dto.toCoin
import com.example.cryptocurrencyy.data.remote.dto.toCoinDetail
import com.example.cryptocurrencyy.domain.model.Coin
import com.example.cryptocurrencyy.domain.model.CoinDetail
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
class GetSingleCoinUseCase @Inject constructor(
    private val repository: ICoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId)
                .toCoinDetail() // convert the CoinDetailDto into Coin Detail
            emit(Resource.Sucsses(coin))
        } catch (e: HttpException) {

            emit(Resource.Error(e.localizedMessage ?: " Un Expected Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))

        }

    }
}