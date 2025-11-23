package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.AddItemsRemoteDatasource.FormDataSource
import com.example.qrbnb_client.data.remote.service.AddItemsRemoteDatasource.FormDataSourceImpl
import com.example.qrbnb_client.data.repository.FormRepositoryImpl
import com.example.qrbnb_client.domain.repository.FormRepository
import com.example.qrbnb_client.domain.usecase.GetAddItemFormUseCase
import com.example.qrbnb_client.presentation.viewmodel.AddItemFormViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val AddItemsModule=module{
    single<FormDataSource>{
        FormDataSourceImpl(get(named("BASE_URL")), httpClient = get(POST_LOGIN_CLIENT))
    }
    single<FormRepository>{
        FormRepositoryImpl(get())
    }
    factory{
        AddItemFormViewModel(get())
    }
    factory{
        GetAddItemFormUseCase(get())
    }
}