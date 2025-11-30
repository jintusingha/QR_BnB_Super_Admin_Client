package com.example.qrbnb_client.data.remote.model.generateQrResponseDto

@kotlinx.serialization.Serializable
data class SeatingDetailResponseDto(
    val id: String,
    val type: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val isActive: Boolean,
    val qrCodeUrl: String? = null,
    val qrSettings: QrSettingsDto
)

@kotlinx.serialization.Serializable
data class QrSettingsDto(
    val includeRoomName: Boolean,
    val layoutStyle: String,
    val size: String
)
