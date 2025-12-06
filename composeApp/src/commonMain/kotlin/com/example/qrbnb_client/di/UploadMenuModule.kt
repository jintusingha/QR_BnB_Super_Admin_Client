package com.example.qrbnb_client.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qrbnb_client.presentation.viewmodel.UploadMenuViewModel
import org.koin.dsl.module

val UploadMenuModule =
    module {

        single {
            UploadMenuViewModel(get())
        }
    }
