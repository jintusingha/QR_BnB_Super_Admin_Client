package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toEntity
import com.example.qrbnb_client.data.remote.service.ordersByDateDataSource.OrdersByDateDataSource
import com.example.qrbnb_client.domain.entity.ordersByDateEntity.OrdersByDateEntity
import com.example.qrbnb_client.domain.repository.OrdersByDateRepository

class OrdersByDateRepositoryImpl(
    private val dataSource: OrdersByDateDataSource
) : OrdersByDateRepository {

    override suspend fun getOrdersByDate(date: String): OrdersByDateEntity {
        val dto = dataSource.getOrdersByDate(date)
        return dto.toEntity()
    }
}