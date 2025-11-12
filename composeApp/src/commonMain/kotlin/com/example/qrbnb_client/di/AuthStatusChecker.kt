package com.example.qrbnb_client.di

import com.example.qrbnb_client.navigation.AuthStatusChecker
import org.koin.dsl.module

val AuthStatusChecker= module {
    single{AuthStatusChecker(get())}
}