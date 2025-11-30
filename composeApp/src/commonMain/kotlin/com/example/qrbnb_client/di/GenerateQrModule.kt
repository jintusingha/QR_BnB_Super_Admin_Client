package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.generateQrRemoteDataSource.GenerateQrRemoteDataSource
import com.example.qrbnb_client.data.remote.service.generateQrRemoteDataSource.GenerateQrRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.GenerateQrRepositoryImpl
import com.example.qrbnb_client.domain.repository.GenerateQrRepository
import com.example.qrbnb_client.domain.usecase.GenerateQrUseCase
import com.example.qrbnb_client.presentation.viewmodel.GenerateQrViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val GenerateQrModule=module{
    single<GenerateQrRemoteDataSource>{ GenerateQrRemoteDataSourceImpl(get(named("BASE_URL")),get(POST_LOGIN_CLIENT)) }
    single<GenerateQrRepository>{ GenerateQrRepositoryImpl(get()) }
    factory{
        GenerateQrUseCase(get())
    }
    factory{
        GenerateQrViewModel(get())
    }
}