package com.example.qrbnb_client.di

import androidx.compose.ui.input.key.Key.Companion.Menu
import com.example.qrbnb_client.data.remote.service.menuConfigurationDatasource.MenuConfigurationRemoteDataSource
import com.example.qrbnb_client.data.remote.service.menuConfigurationDatasource.MenuConfigurationRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.MenuConfigurationRepositoryImpl
import com.example.qrbnb_client.domain.repository.MenuConfigurationRepository
import com.example.qrbnb_client.domain.usecase.MenuConfigurationUseCase
import com.example.qrbnb_client.presentation.viewmodel.MenuConfigurationViewModel
import org.koin.dsl.module

val MenuCOnfigurationModule=module{
    single<MenuConfigurationRemoteDataSource>{ MenuConfigurationRemoteDataSourceImpl() }
    single<MenuConfigurationRepository>{ MenuConfigurationRepositoryImpl(get()) }
    factory{
        MenuConfigurationUseCase(get())
    }
    factory {
        MenuConfigurationViewModel(get())
    }
}