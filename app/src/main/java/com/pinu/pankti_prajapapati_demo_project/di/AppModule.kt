package com.pinu.pankti_prajapapati_demo_project.di

import android.content.Context
import com.pinu.pankti_prajapapati_demo_project.data.network.Network
import com.pinu.pankti_prajapapati_demo_project.data.network.NetworkAPI
import com.pinu.pankti_prajapapati_demo_project.data.repositoryImpl.HoldingsRepositoryImpl
import com.pinu.pankti_prajapapati_demo_project.domain.repository.HoldingsRepository
import com.pinu.pankti_prajapapati_demo_project.domain.viewmodels.HoldingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Singleton
    @Provides
    fun providesNetworkAPI():  NetworkAPI = Network.init().networkAPI

    @Singleton
    @Provides
    fun providesHoldingsRepository(networkAPI: NetworkAPI, @ApplicationContext context: Context): HoldingsRepository {
        return HoldingsRepositoryImpl(networkAPI = networkAPI, context = context)
    }


    @Singleton
    @Provides
    fun providesHoldingsViewModel(holdingsRepository: HoldingsRepository): HoldingsViewModel {
        return HoldingsViewModel(holdingsRepository)
    }


}