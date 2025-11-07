package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.clientDashboardReponse.ClientDashboard

sealed class ClientDashboardState {
    object Loading : ClientDashboardState()

    data class Success(
        val data: ClientDashboard,
    ) : ClientDashboardState()

    data class Error(
        val message: String,
    ) : ClientDashboardState()
}
