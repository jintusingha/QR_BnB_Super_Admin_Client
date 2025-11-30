package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.QrDownloader
import com.example.qrbnb_client.data.QrDownloaderAndroid
import org.koin.dsl.module

val QrImageDownloadModule = module {
    single<QrDownloader> { QrDownloaderAndroid(get()) }
}