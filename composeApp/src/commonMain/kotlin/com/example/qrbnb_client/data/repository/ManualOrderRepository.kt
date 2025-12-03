package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manulOrderRequestDto.ManualOrderRequestDto
import com.example.qrbnb_client.domain.entity.manualOrderEntity.ManualOrderEntity

interface ManualOrderRepository {
    suspend fun createManualOrder(request: ManualOrderRequestDto): ManualOrderEntity
}