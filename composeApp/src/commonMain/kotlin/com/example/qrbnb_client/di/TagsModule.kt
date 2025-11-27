package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.remote.service.tagDatasource.TagRemoteDataSource
import com.example.qrbnb_client.data.remote.service.tagDatasource.TagRemoteDataSourceImpl
import com.example.qrbnb_client.data.repository.TagRepositoryImpl
import com.example.qrbnb_client.domain.repository.TagRepository
import com.example.qrbnb_client.domain.usecase.CreateTagUseCase
import com.example.qrbnb_client.domain.usecase.GetTagsUseCase
import com.example.qrbnb_client.presentation.state.TagUiState
import com.example.qrbnb_client.presentation.viewmodel.TagViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val TagsModule=module{
    single<TagRemoteDataSource>{ TagRemoteDataSourceImpl(get(named("BASE_URL")),get(POST_LOGIN_CLIENT)) }
    single<TagRepository>{ TagRepositoryImpl(get()) }
    factory{CreateTagUseCase(get())}
    factory{ GetTagsUseCase(get()) }
    factory{ TagViewModel(get(),get()) }

}