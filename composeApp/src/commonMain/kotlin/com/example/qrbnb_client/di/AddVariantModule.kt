package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.addVariantRemoteDatasource.AddVariantRemoteDataSource
import com.example.qrbnb_client.data.remote.service.addVariantRemoteDatasource.AddVariantRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.AddVariantRepositoryImpl
import com.example.qrbnb_client.domain.repository.AddVariantRepository
import com.example.qrbnb_client.domain.usecase.AddVariantUseCase
import com.example.qrbnb_client.presentation.viewmodel.AddVariantViewModel
import org.koin.dsl.module

val AddVariantModule =
    module {
        single<AddVariantRemoteDataSource> { AddVariantRemoteDataSourceImpl() }
        single<AddVariantRepository> { AddVariantRepositoryImpl(get()) }
        factory {
            AddVariantUseCase(get())
        }
        factory {
            AddVariantViewModel(get())
        }
    }
