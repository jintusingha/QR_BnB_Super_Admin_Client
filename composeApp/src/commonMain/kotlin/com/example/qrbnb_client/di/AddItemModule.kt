package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.addItemRemoteDatasource.AddItemRemoteDataSource
import com.example.qrbnb_client.data.remote.service.addItemRemoteDatasource.AddItemRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.AddItemRepositoryImpl
import com.example.qrbnb_client.domain.repository.AddItemRepository
import com.example.qrbnb_client.domain.usecase.AddItemUseCase
import com.example.qrbnb_client.presentation.viewmodel.AddItemViewModel
import org.koin.dsl.module

val AddItemModule=module{
    single<AddItemRemoteDataSource>{ AddItemRemoteDataSourceImpl() }
    single<AddItemRepository>{ AddItemRepositoryImpl(get()) }
    factory{
        AddItemUseCase(get())
    }
    factory{
        AddItemViewModel(get())
    }

}