package com.example.qrbnb_client.data.remote.model.ordersByDateDto

import kotlinx.serialization.Serializable

@Serializable
data class OrdersByDateResponseDto(
    val success: Boolean,
    val message: String,
    val data: OrdersByDateDataDto
)
@Serializable
data class OrdersByDateDataDto(
    val totalOrders: Int,
    val totalAmount: Double,
    val orders: List<OrderItemDto>
)
@Serializable
data class OrderItemDto(
    val orderId: String,
    val orderNumber: String,
    val seatingArea: String,
    val total: Double,
    val createdAt: String
)