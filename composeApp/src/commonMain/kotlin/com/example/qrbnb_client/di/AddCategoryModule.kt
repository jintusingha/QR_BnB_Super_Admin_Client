package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.addCategoryDatasource.AddCategoryRemoteDataSource
import com.example.qrbnb_client.data.remote.service.addCategoryDatasource.AddCategoryRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.CategoryRepositoryImpl
import com.example.qrbnb_client.domain.repository.CategoryRepository
import com.example.qrbnb_client.domain.usecase.AddCategoryUseCase
import com.example.qrbnb_client.presentation.viewmodel.AddCategoryViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val AddCategoryModule= module{
    single<AddCategoryRemoteDataSource>{ AddCategoryRemoteDataSourceImpl(get(POST_LOGIN_CLIENT),get(named("BASE_URL"))) }
    single<CategoryRepository>{ CategoryRepositoryImpl(get()) }
    factory{
        AddCategoryUseCase(get())
    }
    factory{
        AddCategoryViewModel(get())
    }
}