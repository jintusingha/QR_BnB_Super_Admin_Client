package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.qrCodeDto.QrCodeItemDto
import com.example.qrbnb_client.data.remote.model.qrCodeDto.QrCodesResponseDto
import com.example.qrbnb_client.domain.entity.qrCodesResponse.QrCodeItem
import com.example.qrbnb_client.domain.entity.qrCodesResponse.QrCodesResponse

fun QrCodesResponseDto.toDomain() = QrCodesResponse(
    success = success,
    data = data.map { it.toDomain() }
)

fun QrCodeItemDto.toDomain() = QrCodeItem(
    id = id,
    name = name,
    type = type,
    qrCodeUrl = qrCodeUrl,
    imageUrl = imageUrl,
    createdAt = createdAt
)