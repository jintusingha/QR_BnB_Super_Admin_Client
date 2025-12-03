package com.example.qrbnb_client.domain.entity.ordersByDateEntity

data class OrdersByDateEntity(
    val totalOrders: Int,
    val totalAmount: Double,
    val orders: List<OrderItemEntity>
)

data class OrderItemEntity(
    val orderId: String,
    val orderNumber: String,
    val seatingArea: String,
    val total: Double,
    val createdAt: String
)