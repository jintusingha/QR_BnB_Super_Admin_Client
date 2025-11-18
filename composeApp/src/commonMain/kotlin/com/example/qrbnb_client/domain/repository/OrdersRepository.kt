package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.orderReponse.OrderEntity

interface OrdersRepository {
    suspend fun getOrders(): List<OrderEntity>
}
