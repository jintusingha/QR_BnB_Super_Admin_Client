package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.ordersByDateDataSource.OrdersByDateDataSource
import com.example.qrbnb_client.data.remote.service.ordersByDateDataSource.OrdersByDateDataSourceImpl
import com.example.qrbnb_client.data.repository.OrdersByDateRepositoryImpl
import com.example.qrbnb_client.domain.repository.OrdersByDateRepository
import com.example.qrbnb_client.domain.usecase.GetOrdersByDateUseCase
import com.example.qrbnb_client.presentation.viewmodel.OrdersCalendarViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val OrdersByDateModule=module{
    single<OrdersByDateDataSource>{ OrdersByDateDataSourceImpl(get(POST_LOGIN_CLIENT),get(named("BASE_URL"))) }
    single<OrdersByDateRepository>{ OrdersByDateRepositoryImpl(get()) }
    factory{
        GetOrdersByDateUseCase(get())
    }
    factory{
        OrdersCalendarViewModel(get())
    }
}