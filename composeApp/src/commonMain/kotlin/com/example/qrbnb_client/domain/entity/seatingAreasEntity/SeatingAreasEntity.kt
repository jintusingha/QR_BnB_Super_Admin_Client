package com.example.qrbnb_client.domain.entity.seatingAreasEntity

data class SeatingAreasEntity(
    val rooms:List<RoomEntity>,
    val tables:List<TableEntity>
)
data class TableEntity(
    val id: String,
    val name: String,
    val description: String,
    val capacity: Int,
    val isActive: Boolean,
    val imageUrl: String,
    val qrCodeUrl: String
)
data class RoomEntity(
    val id: String,
    val name: String,
    val description: String,
    val capacity: Int,
    val isActive: Boolean,
    val imageUrl: String,
    val qrCodeUrl: String
)