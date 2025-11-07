package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.clientDashboardDto.ActivityFeedDataDto
import com.example.qrbnb_client.data.remote.model.clientDashboardDto.ClientDashboardDataDto
import com.example.qrbnb_client.data.remote.model.clientDashboardDto.ClientDashboardResponseDto
import com.example.qrbnb_client.data.remote.model.clientDashboardDto.MenuManagementDataDto
import com.example.qrbnb_client.data.remote.model.clientDashboardDto.QuickStatsDataDto
import com.example.qrbnb_client.domain.entity.clientDashboardReponse.ActivityFeed
import com.example.qrbnb_client.domain.entity.clientDashboardReponse.ClientDashboard
import com.example.qrbnb_client.domain.entity.clientDashboardReponse.MenuManagement
import com.example.qrbnb_client.domain.entity.clientDashboardReponse.QuickStats

fun ClientDashboardResponseDto.toDomain(): ClientDashboard = data.toDomain()

fun ClientDashboardDataDto.toDomain(): ClientDashboard =
    ClientDashboard(
        quickStats = quickStats.toDomain(),
        menuManagement = menuManagement.map { it.toDomain() },
        activityFeed = activityFeed.map { it.toDomain() },
    )

fun QuickStatsDataDto.toDomain(): QuickStats =
    QuickStats(
        categoriesCount = categoriesCount,
        itemsCount = itemsCount,
        ordersCount = ordersCount,
        menuStatus = menuStatus,
    )

fun MenuManagementDataDto.toDomain(): MenuManagement =
    MenuManagement(
        id = id,
        title = title,
        description = description,
        actionLabel = actionLabel,
        iconUrl = iconUrl,
        endpoint = endpoint,
    )

fun ActivityFeedDataDto.toDomain(): ActivityFeed =
    ActivityFeed(
        id = id,
        type = type,
        title = title,
        timeAgo = timeAgo,
        timestamp = timestamp,
    )
