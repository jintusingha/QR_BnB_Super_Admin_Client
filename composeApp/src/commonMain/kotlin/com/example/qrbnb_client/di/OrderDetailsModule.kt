package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.orderDetailsDatasource.OrderDetailsRemoteDatasource
import com.example.qrbnb_client.data.remote.service.orderDetailsDatasource.OrderDetailsRemoteDatasourceImpl
import com.example.qrbnb_client.data.repository.OrderDetailsRepositoryImpl
import com.example.qrbnb_client.domain.repository.OrderDetailsRepository
import com.example.qrbnb_client.domain.usecase.OrderDetailsUseCase
import com.example.qrbnb_client.presentation.viewmodel.OrderDetailsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val OrderDetailsModule =
    module {
        single<OrderDetailsRemoteDatasource> { OrderDetailsRemoteDatasourceImpl() }
        single<OrderDetailsRepository> { OrderDetailsRepositoryImpl(get()) }
        factory {
            OrderDetailsUseCase(get())
        }
        factory {
            OrderDetailsViewModel(get())
        }
    }
