package com.pinu.pankti_prajapapati_demo_project.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object NetworkUtils {

    fun <T> safeApiCall(apiCall: suspend () -> T): Flow<Result<T>> = flow {
        val result = apiCall()
        emit(Result.success(result))
    }.flowOn(Dispatchers.IO).catch { error ->
        emit(Result.failure(error))
    }

}