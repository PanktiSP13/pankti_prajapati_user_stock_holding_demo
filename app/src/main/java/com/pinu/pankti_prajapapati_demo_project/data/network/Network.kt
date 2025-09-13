package com.pinu.pankti_prajapapati_demo_project.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class Network private constructor() {

    private val BASE_URL = "https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io/"

    companion object {


        private var instance: Network? = null

        fun init(): Network {
            if (instance == null) {
                instance = Network()
            }
            return instance as Network
        }
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Converts JSON response to Kotlin objects
            .client(httpClient())
            .build()
    }

    private fun httpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .writeTimeout(60000, TimeUnit.MILLISECONDS)
            .connectTimeout(60000, TimeUnit.MILLISECONDS)
            .readTimeout(60000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                // Enable logging
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(Interceptor { chain ->
                val request: Request.Builder = chain.request().newBuilder()
                //default header
                request.addHeader("Content-Type", "application/json")
                chain.proceed(request.build())
            }).build()
    }


    val networkAPI: NetworkAPI by lazy { retrofit().create(NetworkAPI::class.java) }

}