package com.example.qrbnb_client.data.remote.service.addBadgeRemoteDatasource

import com.example.qrbnb_client.data.remote.model.addBadgeRequestDto.AddBadgeRequestDto
import com.example.qrbnb_client.data.remote.model.addBadgeRequestDto.AddBadgeResponseDto

interface AddBadgeRemoteDataSource {
    suspend fun addBadge(request: AddBadgeRequestDto): AddBadgeResponseDto
}