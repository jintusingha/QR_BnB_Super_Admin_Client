package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.variantRemoteDatasource.VariantRemoteDataSource
import com.example.qrbnb_client.data.remote.service.variantRemoteDatasource.VariantRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.VariantsRepositoryImpl
import com.example.qrbnb_client.domain.repository.VariantsRepository
import com.example.qrbnb_client.domain.usecase.GetVariantsUseCase
import com.example.qrbnb_client.presentation.viewmodel.VariantsViewModel
import org.koin.dsl.module

val VariantsModule=module{
    single<VariantRemoteDataSource>{ VariantRemoteDataSourceImpl() }
    single<VariantsRepository>{ VariantsRepositoryImpl(get()) }
    factory{
        GetVariantsUseCase(get())
    }
    factory{
        VariantsViewModel(get())
    }
}