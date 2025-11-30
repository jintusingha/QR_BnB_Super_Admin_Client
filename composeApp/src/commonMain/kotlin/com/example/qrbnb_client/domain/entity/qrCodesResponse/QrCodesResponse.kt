package com.example.qrbnb_client.domain.entity.qrCodesResponse

data class QrCodesResponse(
    val success: Boolean,
    val data: List<QrCodeItem>
)

data class QrCodeItem(
    val id: String,
    val name: String,
    val type: String,
    val qrCodeUrl: String,
    val imageUrl: String,
    val createdAt: String
)