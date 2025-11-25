package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.imageUploadRemoteDataSource.UploadRemoteDataSource
import com.example.qrbnb_client.data.remote.service.imageUploadRemoteDataSource.UploadRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.UploadRepositoryImpl
import com.example.qrbnb_client.domain.repository.UploadRepository
import com.example.qrbnb_client.domain.usecase.UploadImageUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ImageUploadModule = module {

    // Option 1: If your UploadRemoteDataSourceImpl needs baseUrl
    single<UploadRemoteDataSource> {
        UploadRemoteDataSourceImpl(
            baseUrl = get(named("BASE_URL")),
            httpClient = get(POST_LOGIN_CLIENT),
            tokenStorage = get()
        )
    }

    single<UploadRepository> {
        UploadRepositoryImpl(get())
    }

    single {
        UploadImageUseCase(get(),get())
    }
}