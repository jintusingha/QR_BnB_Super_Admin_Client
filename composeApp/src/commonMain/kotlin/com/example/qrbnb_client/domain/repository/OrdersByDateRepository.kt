package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.ordersByDateEntity.OrdersByDateEntity

interface OrdersByDateRepository {
    suspend fun getOrdersByDate(date: String): OrdersByDateEntity
}