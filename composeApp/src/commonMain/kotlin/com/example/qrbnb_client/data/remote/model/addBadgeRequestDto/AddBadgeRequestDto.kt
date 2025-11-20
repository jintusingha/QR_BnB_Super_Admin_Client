package com.example.qrbnb_client.data.remote.model.addBadgeRequestDto

data class AddBadgeRequestDto(
    val name: String,
    val colorHex: String,
    val description: String?,
    val icon: String?
)