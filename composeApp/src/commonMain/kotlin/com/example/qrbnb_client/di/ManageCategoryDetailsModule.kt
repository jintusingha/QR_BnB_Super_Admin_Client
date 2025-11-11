package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.model.manageCategoryDetailsResponseDto.ManageCategoryDetailsDataSourceImpl
import com.example.qrbnb_client.data.remote.service.manageCategoryDataSource.ManageCategoryDataSource
import com.example.qrbnb_client.data.remote.service.manageCategoryDataSource.ManageCategoryDataSourceImpl
import com.example.qrbnb_client.data.remote.service.manageCategoryDetails.ManageCategoryDetailsDataSource
import com.example.qrbnb_client.data.repository.ManageCategoryDetailsRepositoryImpl
import com.example.qrbnb_client.domain.repository.ManageCategoryDetailsRepository
import com.example.qrbnb_client.domain.usecase.GetManageCategoryDetailUseCase
import com.example.qrbnb_client.presentation.viewmodel.ManageCategoryDetailViewModel
import org.koin.dsl.module

val ManageCategoryDetailsModule= module {
    single< ManageCategoryDetailsDataSource>{ ManageCategoryDetailsDataSourceImpl() }
    single<ManageCategoryDetailsRepository>{ ManageCategoryDetailsRepositoryImpl(get()) }
    factory{
        GetManageCategoryDetailUseCase(get())
    }
    factory { ManageCategoryDetailViewModel(get()) }


}