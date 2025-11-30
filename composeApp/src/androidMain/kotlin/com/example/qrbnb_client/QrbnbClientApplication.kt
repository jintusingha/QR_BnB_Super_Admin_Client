package com.example.qrbnb_client

import android.app.Application
import com.example.qrbnb_client.di.AddBadgeModule
import com.example.qrbnb_client.di.AddCategoryModule
import com.example.qrbnb_client.di.AddItemModule
import com.example.qrbnb_client.di.AddItemsModule
import com.example.qrbnb_client.di.AddModifierGroupModule
import com.example.qrbnb_client.di.AddVariantModule
import com.example.qrbnb_client.di.AuthStatusChecker
import com.example.qrbnb_client.di.ClientDashboardModule
import com.example.qrbnb_client.di.CreateSeatingModule
import com.example.qrbnb_client.di.EditTagModule
import com.example.qrbnb_client.di.GenerateQrModule
import com.example.qrbnb_client.di.ImagePickerModule
import com.example.qrbnb_client.di.ImageUploadModule
import com.example.qrbnb_client.di.ManageCategoryDetailsModule
import com.example.qrbnb_client.di.ManageCategoryModule
import com.example.qrbnb_client.di.MenuCOnfigurationModule
import com.example.qrbnb_client.di.ModifierGroupsModule
import com.example.qrbnb_client.di.OrderDetailsModule
import com.example.qrbnb_client.di.OrdersModule
import com.example.qrbnb_client.di.SeatingAreasModule
import com.example.qrbnb_client.di.SeatingDetailModule
import com.example.qrbnb_client.di.SubmitFormModule
import com.example.qrbnb_client.di.TagsModule
import com.example.qrbnb_client.di.TokenModule
import com.example.qrbnb_client.di.UriHelperModule
import com.example.qrbnb_client.di.VariantsModule
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
            modules(networkModule,otpModule, VerifyOtpModule, TokenModule, ClientDashboardModule,
                ManageCategoryModule, AddCategoryModule, ManageCategoryDetailsModule,
                MenuCOnfigurationModule,AuthStatusChecker, TagsModule, OrdersModule,
                OrderDetailsModule, AddModifierGroupModule,AddVariantModule, AddBadgeModule,
                EditTagModule,ModifierGroupsModule, VariantsModule,AddItemModule,AddItemsModule,
                ImagePickerModule, ImageUploadModule,UriHelperModule, SubmitFormModule,SeatingAreasModule,CreateSeatingModule,GenerateQrModule,SeatingDetailModule
            )
        }
    }
}
