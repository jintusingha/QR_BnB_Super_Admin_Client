package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.stockItemDataSource.StockItemDataSource
import com.example.qrbnb_client.data.remote.service.stockItemDataSource.StockItemDataSourceImpl
import com.example.qrbnb_client.data.repository.StockItemRepositoryImpl
import com.example.qrbnb_client.domain.repository.StockItemRepository
import com.example.qrbnb_client.domain.usecase.GetStockItemsUseCase
import com.example.qrbnb_client.presentation.viewmodel.StockViewModel
import org.koin.dsl.module

val StockModule=module{
    single<StockItemDataSource>{ StockItemDataSourceImpl() }
    single<StockItemRepository>{ StockItemRepositoryImpl(get()) }
    factory{
        GetStockItemsUseCase(get())
    }
    factory{
        StockViewModel(get())
    }
}