package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.editTagRemoteDatasource.EditTagRemoteDataSource
import com.example.qrbnb_client.data.remote.service.editTagRemoteDatasource.EditTagRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.EditTagRepositoryImpl
import com.example.qrbnb_client.domain.repository.EditTagRepository
import com.example.qrbnb_client.domain.usecase.EditTagUseCase
import com.example.qrbnb_client.presentation.viewmodel.EditTagViewModel
import org.koin.dsl.module

val EditTagModule=module{
    single<EditTagRemoteDataSource>{ EditTagRemoteDataSourceImpl() }
    single<EditTagRepository>{ EditTagRepositoryImpl(get()) }
    factory{
        EditTagUseCase(get())

    }
    factory{
        EditTagViewModel(get())
    }

}