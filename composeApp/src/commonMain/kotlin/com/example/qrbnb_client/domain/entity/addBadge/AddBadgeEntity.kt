package com.example.qrbnb_client.domain.entity.addBadge

data class AddBadgeEntity(
    val name: String,
    val colorHex: String,
    val description: String?,
    val icon: String?
)