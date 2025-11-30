package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.generateQrResponseDto.GenerateQrResponseDto
import com.example.qrbnb_client.domain.entity.generateQrEntity.GenerateQrEntity

fun GenerateQrResponseDto.toEntity(): GenerateQrEntity {
    return GenerateQrEntity(
        qrUrl = this.data.qrUrl,
        deepLink = this.data.deepLink
    )
}