package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.orderReponse.OrderEntity
import com.example.qrbnb_client.domain.repository.OrdersRepository

class GetOrdersUseCase(
    private val repository: OrdersRepository,
) {
    suspend operator fun invoke(): List<OrderEntity> = repository.getOrders()
}
