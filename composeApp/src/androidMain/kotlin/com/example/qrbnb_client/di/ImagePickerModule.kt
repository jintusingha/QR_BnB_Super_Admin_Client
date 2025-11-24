package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.AndroidImagePickerHelper
import com.example.qrbnb_client.data.ImagePickerHelper
import org.koin.dsl.module

val ImagePickerModule = module {
    single<ImagePickerHelper> { AndroidImagePickerHelper() }
}