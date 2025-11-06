package com.example.qrbnb_client

import android.app.Application
import com.example.qrbnb_client.di.TokenModule
import com.example.qrbnb_client.di.VerifyOtpModule
import com.example.qrbnb_client.di.networkModule
import com.example.qrbnb_client.di.otpModule
import io.ktor.http.ContentType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class QrbnbClientApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@QrbnbClientApplication)
            modules(networkModule,otpModule, VerifyOtpModule, TokenModule)
        }
    }
}
