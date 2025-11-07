package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.ClientDashboardUseCase
import com.example.qrbnb_client.presentation.state.ClientDashboardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientDashboardViewModel(
    private val clientDashboardUseCase: ClientDashboardUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ClientDashboardState>(ClientDashboardState.Loading)
    val state: StateFlow<ClientDashboardState> = _state

    fun getDashboardData() {
        viewModelScope.launch {
            _state.value = ClientDashboardState.Loading
            try {
                val dashboardData = clientDashboardUseCase()
                _state.value = ClientDashboardState.Success(dashboardData)
            } catch (e: Exception) {
                _state.value =
                    ClientDashboardState.Error(
                        e.message ?: "Something went Wrong",
                    )
            }
        }
    }
}
