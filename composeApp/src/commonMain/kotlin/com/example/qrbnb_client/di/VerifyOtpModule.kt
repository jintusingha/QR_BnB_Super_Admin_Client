package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.TokenStorage
import com.example.qrbnb_client.data.remote.service.verifyOtpRemoteDataSource.VerifyOtpRemoteDataSource
import com.example.qrbnb_client.data.remote.service.verifyOtpRemoteDataSource.VerifyOtpRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.VerifyOtpRepositoryImpl
import com.example.qrbnb_client.domain.repository.VerifyOtpRepository
import com.example.qrbnb_client.domain.usecase.VerifyOtpUseCase
import com.example.qrbnb_client.presentation.viewmodel.VerifyOtpViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val VerifyOtpModule= module {

    single<VerifyOtpRemoteDataSource>{ VerifyOtpRemoteDataSourceImpl(get(PRE_LOGIN_CLIENT),get(named("BASE_URL"))) }
    single<VerifyOtpRepository>{ VerifyOtpRepositoryImpl(get()) }
    factory { VerifyOtpUseCase(get(),get()) }
    factory{ VerifyOtpViewModel(get()) }
}