package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.OtpDataSource.OtpDataSource
import com.example.qrbnb_client.data.remote.service.OtpDataSource.OtpDataSourceImpl
import com.example.qrbnb_client.data.repository.OtpRepositoryImpl
import com.example.qrbnb_client.domain.repository.OtpRepository
import com.example.qrbnb_client.domain.usecase.SendOtpUseCase
import com.example.qrbnb_client.presentation.viewmodel.OtpViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val otpModule=module{
    single<OtpDataSource>{
        OtpDataSourceImpl(httpClient = get(PRE_LOGIN_CLIENT), baseUrl = get(named("BASE_URL")))
    }
    single<OtpRepository>{
        OtpRepositoryImpl(dataSource = get())
    }
    factory{ SendOtpUseCase(get()) }
    factory{ OtpViewModel(get()) }

}


