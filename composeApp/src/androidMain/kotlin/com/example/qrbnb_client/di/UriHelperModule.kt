package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.AndroidUriHelper
import com.example.qrbnb_client.data.UriHelper
import org.koin.dsl.module

val UriHelperModule=module{
    single<UriHelper>{ AndroidUriHelper(get()) }
}