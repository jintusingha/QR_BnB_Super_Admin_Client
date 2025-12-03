package com.example.qrbnb_client.data.remote.model.manualOrderDtos.manualOrderResponseDto

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class ManualOrderResponseDto(
    val success: Boolean,
    val message: String,
    val data: ManualOrderResultDto?
)

@Serializable
data class ManualOrderResultDto(
    val orderId: String,
    val orderNumber: String,
    val total: Double,
    val status: String
)