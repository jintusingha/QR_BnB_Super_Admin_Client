package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.clientDashboardReponse.ClientDashboard
import com.example.qrbnb_client.domain.repository.ClientDashboardRepository


class ClientDashboardUseCase (private val clientDashboardRepository: ClientDashboardRepository){
    suspend operator fun invoke(): ClientDashboard{
        return clientDashboardRepository.getClientDashboardData()
    }
}