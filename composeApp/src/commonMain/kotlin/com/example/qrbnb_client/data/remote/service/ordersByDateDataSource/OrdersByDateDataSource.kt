package com.example.qrbnb_client.data.remote.service.ordersByDateDataSource

import com.example.qrbnb_client.data.remote.model.ordersByDateDto.OrdersByDateResponseDto

interface OrdersByDateDataSource {
    suspend fun getOrdersByDate(date:String): OrdersByDateResponseDto
}