package com.example.qrbnb_client.domain.entity.seatingEntity

data class SeatingEntity(
    val id: String,
    val type: String,
    val name: String,
    val description: String,
    val capacity: Int,
    val isActive: Boolean,
    val imageUrl: String,
    val qrCodeUrl: String
)