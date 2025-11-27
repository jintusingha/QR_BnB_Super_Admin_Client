package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.addModifierGroupRemoteDatasource.AddModifierGroupRemoteDataSource
import com.example.qrbnb_client.data.remote.service.addModifierGroupRemoteDatasource.AddModifierGroupRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.AddModifierGroupRepositoryImpl
import com.example.qrbnb_client.domain.repository.AddModifierGroupRepository
import com.example.qrbnb_client.domain.usecase.AddModifierGroupUseCase
import com.example.qrbnb_client.presentation.viewmodel.AddModifierGroupViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val AddModifierGroupModule= module {
    single<AddModifierGroupRemoteDataSource>{ AddModifierGroupRemoteDataSourceImpl(get(named("BASE_URL")),get(POST_LOGIN_CLIENT)) }
    single<AddModifierGroupRepository>{ AddModifierGroupRepositoryImpl(get()) }
    factory{
        AddModifierGroupUseCase(get())
    }
    factory{
        AddModifierGroupViewModel(get())
    }
}