package com.example.qrbnb_client.data.remote.model.orderResponseDto

data class OrderResponseDto(
    val success: Boolean,
    val data: List<OrderItemDto>,
)

data class OrderItemDto(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val price: Double,
    val status: String,
    val imageUrl: String,
)
