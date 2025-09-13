package com.pinu.pankti_prajapapati_demo_project.di

import com.pinu.pankti_prajapapati_demo_project.data.network.Network
import com.pinu.pankti_prajapapati_demo_project.data.network.NetworkAPI
import com.pinu.pankti_prajapapati_demo_project.data.repositoryImpl.HoldingsRepositoryImpl
import com.pinu.pankti_prajapapati_demo_project.domain.repository.HoldingsRepository
import com.pinu.pankti_prajapapati_demo_project.domain.viewmodels.HoldingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Singleton
    @Provides
    fun providesNetworkAPI():  NetworkAPI  {
        return Network.init().networkAPI
    }

    @Singleton
    @Provides
    fun providesHoldingsRepository(networkAPI: NetworkAPI): HoldingsRepository = HoldingsRepositoryImpl(networkAPI)


    @Singleton
    @Provides
    fun providesHoldingsViewModel(holdingsRepository: HoldingsRepository): HoldingsViewModel = HoldingsViewModel(holdingsRepository)


}