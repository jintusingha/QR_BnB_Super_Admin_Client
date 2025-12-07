package com.example.qrbnb_client.presentation.screen.ClientDashboardScreen

import ActivityItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qrbnb_client.domain.entity.clientDashboardReponse.ClientDashboard
import com.example.qrbnb_client.navigation.ScreenRoute
import com.example.qrbnb_client.presentation.reusableComponents.StatCard
import com.example.qrbnb_client.presentation.screen.clientdashboard.MenuManagementCard
import com.example.qrbnb_client.ui.style_22_28_700

@Composable
fun ClientDashboardContent(
    data: ClientDashboard,
    paddingValues: PaddingValues,
    onOrdersClick: () -> Unit,
    onMenuManagementClick: (String) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
    ) {
        Text(text = "Quick Stats", style = style_22_28_700())
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            StatCard(
                title = "Categories",
                value = data.quickStats.categoriesCount.toString(),
                modifier = Modifier.weight(1f),
                onClick = { /* Handle categories click */ },
            )
            StatCard(
                title = "Items",
                value = data.quickStats.itemsCount.toString(),
                modifier = Modifier.weight(1f),
                onClick = { /* Handle items click */ },
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            StatCard(
                title = "Orders",
                value = data.quickStats.ordersCount.toString(),
                modifier = Modifier.weight(1f),
                onClick = onOrdersClick,
            )
            StatCard(
                title = "Menu Active",
                value = data.quickStats.menuStatus,
                modifier = Modifier.weight(1f),
                onClick = { /* Handle menu status click */ },
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Menu Management", style = style_22_28_700())
        Spacer(modifier = Modifier.height(16.dp))

        data.menuManagement.forEach { menu ->
            MenuManagementCard(
                title = menu.title,
                description = menu.description,
                actionLabel = menu.actionLabel,
                iconUrl = menu.iconUrl,
                onClick = {
                    onMenuManagementClick(menu.endpoint)
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Recent Activity Feed", style = style_22_28_700())
        Spacer(modifier = Modifier.height(16.dp))

        data.activityFeed.forEach { activity ->
            ActivityItem(
                title = activity.title,
                timeAgo = activity.timeAgo,
                type = activity.type,
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
