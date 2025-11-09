package com.example.qrbnb_client

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.example.qrbnb_client.presentation.screen.AddCategoryScreen

import com.example.qrbnb_client.presentation.screen.manageCategoryScreen.ManageCategoriesScreen

import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
//        OtpScreen()
//        val navController = rememberNavController()
//        AppNavHost(navController)
//        ClientDashboardScreen()
//        ManageCategoriesScreen {  }
        AddCategoryScreen()



    }
}