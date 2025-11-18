package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.orderRemoteDatasource.OrderRemoteDatasource
import com.example.qrbnb_client.data.remote.service.orderRemoteDatasource.OrderRemoteDatasourceImpl
import com.example.qrbnb_client.data.repository.OrdersRepositoryImpl
import com.example.qrbnb_client.domain.repository.OrdersRepository
import com.example.qrbnb_client.domain.usecase.GetOrdersUseCase
import com.example.qrbnb_client.presentation.viewmodel.OrdersViewModel
import org.koin.dsl.module

val OrdersModule= module {
    single<OrderRemoteDatasource>{ OrderRemoteDatasourceImpl() }
    single<OrdersRepository>{ OrdersRepositoryImpl(get()) }
    factory{
        GetOrdersUseCase(get())
    }
    factory{
        OrdersViewModel(get())
    }
}