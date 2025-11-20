package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.addBadgeRemoteDatasource.AddBadgeRemoteDataSource
import com.example.qrbnb_client.data.remote.service.addBadgeRemoteDatasource.AddBadgeRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.AddBadgeRepositoryImpl
import com.example.qrbnb_client.domain.repository.AddBadgeRepository
import com.example.qrbnb_client.domain.usecase.AddBadgeUseCase
import com.example.qrbnb_client.presentation.viewmodel.AddBadgeViewModel
import org.koin.dsl.module

val AddBadgeModule=module{
    single<AddBadgeRemoteDataSource>{ AddBadgeRemoteDataSourceImpl() }
    single<AddBadgeRepository>{ AddBadgeRepositoryImpl(get()) }
    factory{
        AddBadgeUseCase(get())
    }
    factory{
        AddBadgeViewModel(get())
    }
}