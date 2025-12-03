package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toEntity
import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manulOrderRequestDto.ManualOrderRequestDto
import com.example.qrbnb_client.data.remote.service.manualOrdeRemoteDataSource.ManualOrderRemoteDataSource
import com.example.qrbnb_client.domain.entity.manualOrderEntity.ManualOrderEntity

class ManualOrderRepositoryImpl(
    private val dataSource: ManualOrderRemoteDataSource
) : ManualOrderRepository {

    override suspend fun createManualOrder(request: ManualOrderRequestDto): ManualOrderEntity {
        val response = dataSource.createManualOrder(request)

        if (!response.success || response.data == null) {
            throw Exception(response.message)
        }

        return response.data.toEntity()
    }
}