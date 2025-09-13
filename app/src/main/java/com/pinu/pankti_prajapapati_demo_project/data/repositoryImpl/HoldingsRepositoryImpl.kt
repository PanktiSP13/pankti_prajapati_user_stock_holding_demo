package com.pinu.pankti_prajapapati_demo_project.data.repositoryImpl

import android.content.Context
import com.pinu.pankti_prajapapati_demo_project.data.network.NetworkAPI
import com.pinu.pankti_prajapapati_demo_project.data.network.NetworkUtils
import com.pinu.pankti_prajapapati_demo_project.data.network.NoInternetException
import com.pinu.pankti_prajapapati_demo_project.domain.model.HoldingsResponse
import com.pinu.pankti_prajapapati_demo_project.domain.repository.HoldingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class HoldingsRepositoryImpl @Inject constructor(
    private val context: Context,
    private val networkAPI: NetworkAPI,
) : HoldingsRepository {

    override suspend fun fetchHoldings(): Flow<Result<HoldingsResponse>> {
        if (!NetworkUtils.hasInternetConnection(context)) {
            return flow { emit(Result.failure(NoInternetException())) }
        }
        return NetworkUtils.safeApiCall { networkAPI.fetchHoldings() }
    }
}