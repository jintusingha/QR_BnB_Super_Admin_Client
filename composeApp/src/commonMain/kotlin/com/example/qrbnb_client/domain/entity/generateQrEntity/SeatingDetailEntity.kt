package com.example.qrbnb_client.domain.entity.generateQrEntity

data class SeatingDetailEntity(
    val id: String,
    val type: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val isActive: Boolean,
    val qrCodeUrl: String? = null,
    val qrSettings: QrSettingsEntity
)

data class QrSettingsEntity(
    val includeRoomName: Boolean,
    val layoutStyle: String, // ex: Rounded, Minimal, Bold
    val size: String // ex: Small, Medium, Large
)
