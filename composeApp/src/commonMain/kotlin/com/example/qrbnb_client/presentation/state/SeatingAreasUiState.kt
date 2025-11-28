package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.seatingAreasEntity.RoomEntity
import com.example.qrbnb_client.domain.entity.seatingAreasEntity.TableEntity

data class SeatingAreasUiState(
    val isLoading: Boolean = false,
    val rooms: List<RoomEntity> = emptyList(),
    val tables: List<TableEntity> = emptyList(),
    val error: String? = null
)