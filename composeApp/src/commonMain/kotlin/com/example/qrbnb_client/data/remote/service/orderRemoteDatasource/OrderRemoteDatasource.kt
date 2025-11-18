package com.example.qrbnb_client.data.remote.service.orderRemoteDatasource

import com.example.qrbnb_client.data.remote.model.orderResponseDto.OrderItemDto
import com.example.qrbnb_client.data.remote.model.orderResponseDto.OrderResponseDto

interface OrderRemoteDatasource {
    suspend fun getOrder(): OrderResponseDto
}
