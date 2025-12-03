package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.ordersByDateEntity.OrdersByDateEntity
import com.example.qrbnb_client.domain.repository.OrdersByDateRepository

class GetOrdersByDateUseCase(
    private val repository: OrdersByDateRepository
) {
    suspend operator fun invoke(date: String): OrdersByDateEntity {
        return repository.getOrdersByDate(date)
    }
}