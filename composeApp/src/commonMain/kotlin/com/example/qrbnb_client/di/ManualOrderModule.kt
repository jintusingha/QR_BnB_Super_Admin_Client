package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.manualOrdeRemoteDataSource.ManualOrderRemoteDataSource
import com.example.qrbnb_client.data.remote.service.manualOrdeRemoteDataSource.ManualOrderRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.ManualOrderRepository
import com.example.qrbnb_client.data.repository.ManualOrderRepositoryImpl
import com.example.qrbnb_client.domain.usecase.CreateManualOrderUseCase
import com.example.qrbnb_client.presentation.viewmodel.ManualOrderViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ManualOrderModule=module{
    single<ManualOrderRemoteDataSource>{ ManualOrderRemoteDataSourceImpl(get(POST_LOGIN_CLIENT),get(named("BASE_URL"))) }
    single<ManualOrderRepository>{ ManualOrderRepositoryImpl(get()) }
    factory {
        CreateManualOrderUseCase(get())
    }
    factory{
        ManualOrderViewModel(get())
    }
}