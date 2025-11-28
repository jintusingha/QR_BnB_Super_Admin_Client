package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.createSeatingRemoteDataSource.CreateSeatingRemoteDataSource
import com.example.qrbnb_client.data.remote.service.createSeatingRemoteDataSource.CreateSeatingRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.CreateSeatingRepositoryImpl
import com.example.qrbnb_client.domain.repository.CreateSeatingRepository
import com.example.qrbnb_client.domain.usecase.CreateSeatingUseCase
import com.example.qrbnb_client.presentation.viewmodel.CreateSeatingViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val CreateSeatingModule=module{
    single<CreateSeatingRemoteDataSource>{ CreateSeatingRemoteDataSourceImpl(get(named("BASE_URL")),get(POST_LOGIN_CLIENT)) }
    single<CreateSeatingRepository>{ CreateSeatingRepositoryImpl(get()) }
    factory{
        CreateSeatingUseCase(get())
    }
    factory{
        CreateSeatingViewModel(get())
    }
}