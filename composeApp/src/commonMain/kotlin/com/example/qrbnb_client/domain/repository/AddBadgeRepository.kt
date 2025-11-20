package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.data.remote.model.addBadgeRequestDto.AddBadgeResponseDto
import com.example.qrbnb_client.domain.entity.addBadge.AddBadgeEntity

interface AddBadgeRepository {
    suspend fun addBadge(entity: AddBadgeEntity): AddBadgeResponseDto
}