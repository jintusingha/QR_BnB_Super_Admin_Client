package com.example.qrbnb_client.data.remote.service.manualOrdeRemoteDataSource

import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manualOrderResponseDto.ManualOrderResponseDto
import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manulOrderRequestDto.ManualOrderRequestDto

interface ManualOrderRemoteDataSource {
    suspend fun createManualOrder(request: ManualOrderRequestDto): ManualOrderResponseDto
}