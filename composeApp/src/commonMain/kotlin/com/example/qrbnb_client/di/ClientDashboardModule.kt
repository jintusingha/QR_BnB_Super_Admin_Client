package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.clientDashboardDataSource.ClientDashboardDataSource
import com.example.qrbnb_client.data.remote.service.clientDashboardDataSource.ClientDashboardDatasourceImpl
import com.example.qrbnb_client.data.repository.ClientDashboardRepositoryImpl
import com.example.qrbnb_client.domain.repository.ClientDashboardRepository
import com.example.qrbnb_client.domain.usecase.ClientDashboardUseCase
import com.example.qrbnb_client.presentation.viewmodel.ClientDashboardViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ClientDashboardModule =
    module {
        single<ClientDashboardDataSource> { ClientDashboardDatasourceImpl(get(named("BASE_URL")), get(POST_LOGIN_CLIENT)) }
        single<ClientDashboardRepository> { ClientDashboardRepositoryImpl(get()) }
        factory { ClientDashboardUseCase(get()) }
        factory { ClientDashboardViewModel(get()) }
    }
