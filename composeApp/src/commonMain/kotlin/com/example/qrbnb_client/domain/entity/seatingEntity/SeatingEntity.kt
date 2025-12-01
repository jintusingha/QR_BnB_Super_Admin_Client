package com.example.qrbnb_client.domain.entity.seatingEntity

data class SeatingEntity(
    val id: String,
    val name: String,
    val type: String,
    val qrCodeUrl: String,
    val imageUrl: String,
    val createdAt: String
)