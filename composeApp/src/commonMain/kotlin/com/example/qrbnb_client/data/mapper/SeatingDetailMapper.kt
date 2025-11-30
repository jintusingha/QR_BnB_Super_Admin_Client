package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.generateQrResponseDto.QrSettingsDto
import com.example.qrbnb_client.data.remote.model.generateQrResponseDto.SeatingDetailResponseDto
import com.example.qrbnb_client.domain.entity.generateQrEntity.QrSettingsEntity
import com.example.qrbnb_client.domain.entity.generateQrEntity.SeatingDetailEntity

fun SeatingDetailResponseDto.toEntity() = SeatingDetailEntity(
    id = id,
    type = type,
    name = name,
    description = description,
    imageUrl = imageUrl,
    isActive = isActive,
    qrCodeUrl = qrCodeUrl,
    qrSettings = qrSettings.toEntity()
)

fun QrSettingsDto.toEntity() = QrSettingsEntity(
    includeRoomName = includeRoomName,
    layoutStyle = layoutStyle,
    size = size
)
