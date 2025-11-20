package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.modifierGroupRemoteDatasource.ModifierGroupsRemoteDataSource
import com.example.qrbnb_client.data.remote.service.modifierGroupRemoteDatasource.ModifierGroupsRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.ModifierGroupsRepositoryImpl
import com.example.qrbnb_client.domain.repository.ModifierGroupsRepository
import com.example.qrbnb_client.domain.usecase.GetModifierGroupsUseCase
import com.example.qrbnb_client.presentation.viewmodel.ModifierGroupsViewModel
import org.koin.dsl.module

val ModifierGroupsModule=module{
    single<ModifierGroupsRemoteDataSource>{ ModifierGroupsRemoteDataSourceImpl() }
    single<ModifierGroupsRepository>{ ModifierGroupsRepositoryImpl(get()) }
    factory{
        GetModifierGroupsUseCase(get())
    }
    factory{
        ModifierGroupsViewModel(get())
    }
}