package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.orderListDataSource.OrderListDataSource
import com.example.qrbnb_client.data.remote.service.orderListDataSource.OrderListDataSourceImpl
import com.example.qrbnb_client.data.repository.OrderListRepositoryImpl
import com.example.qrbnb_client.domain.repository.OrderListRepository
import com.example.qrbnb_client.domain.usecase.GetOrdersListUseCase
import com.example.qrbnb_client.presentation.viewmodel.OrderListViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val OrderListModule = module {

    single<OrderListDataSource> {
        OrderListDataSourceImpl(
            get(named("POST_LOGIN_CLIENT")),
            get(named("BASE_URL"))
        )
    }

    single<OrderListRepository> { OrderListRepositoryImpl(get()) }

    factory { GetOrdersListUseCase(get()) }

    factory { OrderListViewModel(get()) }
}
