package com.example.qrbnb_client.domain.entity.orderReponse

data class OrderEntity(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val price: Double,
    val status: String,
    val imageUrl: String,
)
