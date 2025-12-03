package com.example.qrbnb_client

import AppNavHost
import CheckoutScreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.example.qrbnb_client.navigation.AuthStatusChecker
import com.example.qrbnb_client.presentation.screen.ManualOrderScreen
import com.example.qrbnb_client.presentation.screen.OrdersScreen
import com.example.qrbnb_client.presentation.screen.QrCodesScreen
import com.example.qrbnb_client.presentation.screen.addCategoryScreen.AddCategoryScreen

import com.example.qrbnb_client.presentation.screen.orderDetailsScreen.OrderDetailsScreen
import com.example.qrbnb_client.presentation.screen.ordersCalendarScreen.OrdersCalendarScreen


import com.example.qrbnb_client.presentation.screen.ordersListScreen.OrdersListScreen
import com.example.qrbnb_client.presentation.screen.seatingSelectionScreen.SeatingSelectionScreen
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
//        AddModifierGroupScreen(onBackClick  = {})
//        AddVariantScreen(onBack = {}, onSuccess = {})
//        AddBadgeScreen(onBackClick = {})
//        EditTagScreen(tagId = "1","food", onBack = {}, onCancel = {}, onDelete = {})
//        ModifierGroupsScreen(onBackClick = {}, onAddGroupClick = {}, onEditGroupClick = {})

//        VariantsScreen(onBackClick = {}, onAddGroupClick = {}, onEditGroupClick = {})
//        AddItemScreen(onBack = {})
//        DynamicAddItemScreen(onBack = {})
//        RoomsAndTablesScreen()
//        CreateSeatingScreen(onSuccess = {}, onBackClick = {})
//        GenerateQrScreen(seatingId = "3", onBackClick = {}, onSuccess = { qrUrl, deepLink ->
//        })
//        OrdersListScreen(clientId = "5", onBackClick = {}, onOrderClick = {})
//        QrCodesScreen(onBackClick = {}, onQrCodeClick = {})
//        SeatingSelectionScreen(onSeatingSelected = {}, onNextClick = {}, onCloseClick = {})
//        ManualOrderScreen(onCloseClick = {})

//        CheckoutScreen(onCloseClick = {}, onOrderSuccess = {orderId, orderNumber -> {}})
        OrdersCalendarScreen(onBackClick = {})
    }
}
