package com.example.qrbnb_client.data.remote.service.orderDetailsDatasource

import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrdersDetailsResponseDto

interface OrderDetailsRemoteDatasource {
    suspend fun getOrderDetails(orderId: String): OrdersDetailsResponseDto
}
