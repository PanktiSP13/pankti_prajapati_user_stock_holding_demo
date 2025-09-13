package com.pinu.pankti_prajapapati_demo_project.data.network

import com.pinu.pankti_prajapapati_demo_project.domain.model.HoldingsResponse
import retrofit2.http.GET

interface NetworkAPI {

    @GET("/")
    suspend fun getHoldings(): HoldingsResponse
}