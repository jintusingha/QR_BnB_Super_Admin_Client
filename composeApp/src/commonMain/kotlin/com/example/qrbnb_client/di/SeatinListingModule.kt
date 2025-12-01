package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.seatingListDataSource.SeatingDataSource
import com.example.qrbnb_client.data.remote.service.seatingListDataSource.SeatingDataSourceImpl
import com.example.qrbnb_client.data.repository.SeatingRepositoryImpl
import com.example.qrbnb_client.domain.repository.SeatingRepository
import com.example.qrbnb_client.domain.usecase.GetSeatingListUseCase
import com.example.qrbnb_client.presentation.viewmodel.SeatingViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val SeatingListingModule=module{
    single<SeatingDataSource>{ SeatingDataSourceImpl(get(POST_LOGIN_CLIENT),get(named("BASE_URL"))) }
    single<SeatingRepository>{ SeatingRepositoryImpl(get()) }
    factory{
        GetSeatingListUseCase(get())
    }
    factory{
        SeatingViewModel(get())
    }
}