package com.example.qrbnb_client.data.remote.model.clientDashboardDto

import kotlinx.serialization.Serializable

@Serializable
data class ClientDashboardResponseDto(
    val success: Boolean,
    val message: String,
    val data: ClientDashboardDataDto,
)
@Serializable
data class ClientDashboardDataDto(
    val quickStats: QuickStatsDataDto,
    val menuManagement: List<MenuManagementDataDto>,
    val activityFeed: List<ActivityFeedDataDto>,
)
@Serializable
data class QuickStatsDataDto(
    val categoriesCount: Int,
    val itemsCount: Int,
    val ordersCount: Int,
    val menuStatus: String,
)
@Serializable
data class MenuManagementDataDto(
    val id: String,
    val title: String,
    val description: String,
    val actionLabel: String,
    val iconUrl: String,
    val endpoint: String,
)
@Serializable
data class ActivityFeedDataDto(
    val id: String,
    val type: String,
    val title: String,
    val timeAgo: String,
    val timestamp: String,
)
