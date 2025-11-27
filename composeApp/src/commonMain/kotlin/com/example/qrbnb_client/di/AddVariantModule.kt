package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.addVariantRemoteDatasource.AddVariantRemoteDataSource
import com.example.qrbnb_client.data.remote.service.addVariantRemoteDatasource.AddVariantRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.AddVariantRepositoryImpl
import com.example.qrbnb_client.domain.repository.AddVariantRepository
import com.example.qrbnb_client.domain.usecase.AddVariantUseCase
import com.example.qrbnb_client.presentation.viewmodel.AddVariantViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val AddVariantModule =
    module {
        single<AddVariantRemoteDataSource> { AddVariantRemoteDataSourceImpl(get(named("BASE_URL")),get(POST_LOGIN_CLIENT)) }
        single<AddVariantRepository> { AddVariantRepositoryImpl(get()) }
        factory {
            AddVariantUseCase(get())
        }
        factory {
            AddVariantViewModel(get())
        }
    }
