package com.pinu.pankti_prajapapati_demo_project.data.repositoryImpl

import com.pinu.pankti_prajapapati_demo_project.data.network.NetworkAPI
import com.pinu.pankti_prajapapati_demo_project.data.network.NetworkUtils
import com.pinu.pankti_prajapapati_demo_project.domain.model.HoldingsResponse
import com.pinu.pankti_prajapapati_demo_project.domain.repository.HoldingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class HoldingsRepositoryImpl @Inject constructor(private val networkAPI: NetworkAPI) : HoldingsRepository {

    override suspend fun fetchHoldings(): Flow<Result<HoldingsResponse>> {
        return NetworkUtils.safeApiCall { networkAPI.getHoldings() }
    }
}