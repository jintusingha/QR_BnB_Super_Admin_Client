package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.generateQrRemoteDataSource.SeatingDetailRemoteDataSource
import com.example.qrbnb_client.data.remote.service.generateQrRemoteDataSource.SeatingDetailRemoteDataSourceDummyImpl
import com.example.qrbnb_client.data.repository.SeatingDetailRepositoryImpl
import com.example.qrbnb_client.domain.repository.SeatingDetailRepository
import com.example.qrbnb_client.domain.usecase.GetSeatingDetailUseCase
import com.example.qrbnb_client.presentation.viewmodel.SeatingDetailViewModel
import org.koin.dsl.module

val SeatingDetailModule=module{
    single<SeatingDetailRemoteDataSource> { SeatingDetailRemoteDataSourceDummyImpl() }
    single<SeatingDetailRepository>{ SeatingDetailRepositoryImpl(get()) }

    factory{ GetSeatingDetailUseCase(get()) }
    factory { SeatingDetailViewModel(get()) }
}