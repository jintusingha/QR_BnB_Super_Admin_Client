package com.example.qrbnb_client

import AppNavHost
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.qrbnb_client.di.AddModifierGroupModule
import com.example.qrbnb_client.navigation.AuthStatusChecker
import com.example.qrbnb_client.presentation.screen.AddCategoryScreen
import com.example.qrbnb_client.presentation.screen.AddModifierGroupScreen
import com.example.qrbnb_client.presentation.screen.AddVariantScreen
import com.example.qrbnb_client.presentation.screen.MenuConfigurationScreen
import com.example.qrbnb_client.presentation.screen.OrdersScreen


import com.example.qrbnb_client.presentation.screen.manageCategoryDetailsScreen.ManageCategoryDetailScreen
import com.example.qrbnb_client.presentation.screen.manageCategoryScreen.ManageCategoriesScreen
import com.example.qrbnb_client.presentation.screen.orderDetailsScreen.OrderDetailsScreen
import com.example.qrbnb_client.presentation.screen.tagScreen.TagsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
fun App() {
    MaterialTheme {
//        OtpScreen()
//        val authStatusChecker= koinInject<AuthStatusChecker>()
//        AppNavHost(authStatusChecker)
//        ClientDashboardScreen()
//        ManageCategoriesScreen {  }
//        AddCategoryScreen()
//        ManageCategoryDetailScreen(categoryId = "")
//        MenuConfigurationScreen()
//        TagsScreen(onBackClick = {})
//        OrdersScreen(onBackClick = {})
//        OrderDetailsScreen(onBackClick = {})
//        AddModifierGroupScreen(onBackClick = {})
        AddVariantScreen(onBack = {}, onSuccess = {})
    }
}
