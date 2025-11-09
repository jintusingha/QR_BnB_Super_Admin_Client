package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.manageCategoryDataSource.ManageCategoryDataSource
import com.example.qrbnb_client.data.remote.service.manageCategoryDataSource.ManageCategoryDataSourceImpl
import com.example.qrbnb_client.data.repository.ManageCategoryRepositoryImpl
import com.example.qrbnb_client.domain.repository.ManageCategoryRepository
import com.example.qrbnb_client.domain.usecase.DeleteCategoryUseCase
import com.example.qrbnb_client.domain.usecase.ManageCategoryUseCase
import com.example.qrbnb_client.presentation.viewmodel.ManageCategoryViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ManageCategoryModule= module {
    single<ManageCategoryDataSource>{ ManageCategoryDataSourceImpl(get((POST_LOGIN_CLIENT)),get(named("BASE_URL"))) }
    single<ManageCategoryRepository> { ManageCategoryRepositoryImpl(get()) }
    factory {
        ManageCategoryUseCase(get())
    }
    factory{
        DeleteCategoryUseCase(get())
    }
    factory {
        ManageCategoryViewModel(get(),get())
    }
}