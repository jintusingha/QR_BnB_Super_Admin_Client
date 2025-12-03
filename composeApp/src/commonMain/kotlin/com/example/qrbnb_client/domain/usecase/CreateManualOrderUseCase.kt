package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manulOrderRequestDto.ManualOrderRequestDto
import com.example.qrbnb_client.data.repository.ManualOrderRepository
import com.example.qrbnb_client.domain.entity.manualOrderEntity.ManualOrderEntity

class CreateManualOrderUseCase(
    private val repository: ManualOrderRepository
) {
    suspend operator fun invoke(request: ManualOrderRequestDto): ManualOrderEntity {
        return repository.createManualOrder(request)
    }
}