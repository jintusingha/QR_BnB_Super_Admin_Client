package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.AndroidTokenStorage
import com.example.qrbnb_client.data.TokenStorage
import org.koin.dsl.module

val TokenModule= module {
    single<TokenStorage>{ AndroidTokenStorage(get()) }

}