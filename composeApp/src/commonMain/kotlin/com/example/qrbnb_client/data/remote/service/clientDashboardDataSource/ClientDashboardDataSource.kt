package com.example.qrbnb_client.data.remote.service.clientDashboardDataSource

import com.example.qrbnb_client.data.remote.model.clientDashboardDto.ClientDashboardResponseDto

interface ClientDashboardDataSource {
    suspend fun getClientDashboardData(): ClientDashboardResponseDto
}