package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.AddItemsRemoteDatasource.ItemDataSourceImpl
import com.example.qrbnb_client.data.remote.service.addItemFormSubmitDataSource.ItemDataSource

import com.example.qrbnb_client.data.repository.ItemRepositoryImpl
import com.example.qrbnb_client.domain.repository.ItemRepository
import com.example.qrbnb_client.domain.usecase.SubmitItemUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val SubmitFormModule =
    module {
        single<ItemDataSource> {
            ItemDataSourceImpl(
                get(POST_LOGIN_CLIENT),
                get(named("BASE_URL"))
            )
        }
        single<ItemRepository> { ItemRepositoryImpl(get()) }

        factory {
            SubmitItemUseCase(get())
        }
    }
