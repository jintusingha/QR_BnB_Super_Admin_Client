package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.qrCodeDataSource.QrCodesDataSource
import com.example.qrbnb_client.data.remote.service.qrCodeDataSource.QrCodesDataSourceImpl
import com.example.qrbnb_client.data.repository.QrCodesRepositoryImpl
import com.example.qrbnb_client.domain.repository.QrCodesRepository
import com.example.qrbnb_client.domain.usecase.GetQrCodesUseCase
import com.example.qrbnb_client.presentation.viewmodel.QrCodesViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val QrCodesModule=module{
    single<QrCodesDataSource>{ QrCodesDataSourceImpl(get(POST_LOGIN_CLIENT),get(named("BASE_URL"))) }
    single<QrCodesRepository>{ QrCodesRepositoryImpl(get()) }
    factory{
        GetQrCodesUseCase(get())
    }
    factory{
        QrCodesViewModel(get())
    }
}