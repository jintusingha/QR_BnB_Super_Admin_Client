package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.clientDashboardReponse.ClientDashboard

interface ClientDashboardRepository {
    suspend fun getClientDashboardData(): ClientDashboard
}