package com.pinu.pankti_prajapapati_demo_project.domain.repository

import com.pinu.pankti_prajapapati_demo_project.domain.model.HoldingsResponse
import kotlinx.coroutines.flow.Flow

interface HoldingsRepository {

    suspend fun fetchHoldings(): Flow<Result<HoldingsResponse>>
}