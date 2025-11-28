package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.seatingAreasRemoteDataSource.SeatingAreasRemoteDataSource
import com.example.qrbnb_client.data.remote.service.seatingAreasRemoteDataSource.SeatingAreasRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.SeatingAreasRepositoryImpl
import com.example.qrbnb_client.domain.repository.SeatingAreasRepository
import com.example.qrbnb_client.domain.usecase.GetSeatingAreasUseCase
import com.example.qrbnb_client.presentation.viewmodel.SeatingAreasViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val SeatingAreasModule=module{
    single<SeatingAreasRemoteDataSource>{ SeatingAreasRemoteDataSourceImpl(get(named("BASE_URL")),get(POST_LOGIN_CLIENT)) }
    single<SeatingAreasRepository>{ SeatingAreasRepositoryImpl(get()) }
    factory{
        GetSeatingAreasUseCase(get())
    }
    factory{
        SeatingAreasViewModel(get())
    }
}