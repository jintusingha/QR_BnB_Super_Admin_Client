package com.example.qrbnb_client.data.remote.model

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class RefreshRequest(
    val phoneNumber: String,
    val refreshToken: String,
)

@kotlinx.serialization.Serializable
data class RefreshData(
    val userId: String,
    val clientId: String,
    val accessToken: String,
    val expiresIn: Long,
)

@Serializable
data class RefreshResponse(
    val success: Boolean,
    val message: String,
    val data: RefreshData,
)
