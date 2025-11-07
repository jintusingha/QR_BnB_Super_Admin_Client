package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomain
import com.example.qrbnb_client.data.remote.service.clientDashboardDataSource.ClientDashboardDataSource
import com.example.qrbnb_client.domain.entity.clientDashboardReponse.ClientDashboard
import com.example.qrbnb_client.domain.repository.ClientDashboardRepository

class ClientDashboardRepositoryImpl(
    private val datasource: ClientDashboardDataSource,
) : ClientDashboardRepository {
    override suspend fun getClientDashboardData(): ClientDashboard {
        val result = datasource.getClientDashboardData()
        return result.toDomain()
    }
}
