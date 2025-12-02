package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.menuDataSource.MenuDataSource
import com.example.qrbnb_client.data.remote.service.menuDataSource.MenuDataSourceImpl
import com.example.qrbnb_client.data.repository.MenuRepositoryImpl
import com.example.qrbnb_client.domain.repository.MenuRepository
import com.example.qrbnb_client.domain.usecase.GetMenuUseCase
import com.example.qrbnb_client.presentation.viewmodel.MenuViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ManualMenuModule=module{
    single<MenuDataSource>{ MenuDataSourceImpl(get(POST_LOGIN_CLIENT),get(named("BASE_URL"))) }
    single<MenuRepository>{ MenuRepositoryImpl(get()) }
    factory{
        GetMenuUseCase(get())
    }
    factory{
        MenuViewModel(get())
    }
}