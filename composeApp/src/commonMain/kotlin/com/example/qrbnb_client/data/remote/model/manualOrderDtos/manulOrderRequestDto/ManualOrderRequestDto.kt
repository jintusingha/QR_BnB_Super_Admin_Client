package com.example.qrbnb_client.data.remote.model.manualOrderDtos.manulOrderRequestDto

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class ManualOrderRequestDto(
    val seatingAreaId: String,
    val customerName: String?,
    val customerPhone: String?,
    val notes: String?,
    val items: List<ManualOrderItemDto>
)

@Serializable
data class ManualOrderItemDto(
    val itemId: String,
    val quantity: Int
)