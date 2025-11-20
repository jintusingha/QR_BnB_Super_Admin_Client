package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderDetailsEntity

interface OrderDetailsRepository {
    suspend fun getOrderDetails(orderId: String): OrderDetailsEntity
}
