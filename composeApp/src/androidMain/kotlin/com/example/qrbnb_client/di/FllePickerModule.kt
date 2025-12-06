package com.example.qrbnb_client.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qrbnb_client.data.AndroidFilePicker
import com.example.qrbnb_client.data.IFilePicker
import com.example.qrbnb_client.presentation.viewmodel.UploadMenuViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val filePickerModule = module {
    single<IFilePicker> {
        AndroidFilePicker(androidContext())
    }
}

