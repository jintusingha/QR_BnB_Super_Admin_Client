package com.example.qrbnb_client.domain.entity.clientDashboardReponse

data class ClientDashboard(
    val quickStats: QuickStats,
    val menuManagement: List<MenuManagement>,
    val activityFeed: List<ActivityFeed>,
)

data class QuickStats(
    val categoriesCount: Int,
    val itemsCount: Int,
    val ordersCount: Int,
    val menuStatus: String,
)

data class MenuManagement(
    val id: String,
    val title: String,
    val description: String,
    val actionLabel: String,
    val iconUrl: String,
    val endpoint: String,
)

data class ActivityFeed(
    val id: String,
    val type: String,
    val title: String,
    val timeAgo: String,
    val timestamp: String,
)
