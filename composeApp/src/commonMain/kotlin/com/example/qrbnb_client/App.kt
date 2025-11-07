package com.example.qrbnb_client

import AppNavHost
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.qrbnb_client.presentation.screen.ClientDashboardScreen.ClientDashboardScreen

import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
//        OtpScreen()
//        val navController = rememberNavController()
//        AppNavHost(navController)
        ClientDashboardScreen()



    }
}