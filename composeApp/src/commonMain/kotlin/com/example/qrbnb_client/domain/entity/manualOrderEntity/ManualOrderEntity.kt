package com.example.qrbnb_client.domain.entity.manualOrderEntity

data class ManualOrderEntity(
    val orderId: String,
    val orderNumber: String,
    val total: Double,
    val status: String
)