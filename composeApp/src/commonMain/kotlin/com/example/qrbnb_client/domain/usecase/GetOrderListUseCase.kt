package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.orderListResponse.OrderListData
import com.example.qrbnb_client.domain.repository.OrderListRepository

class GetOrdersListUseCase(
    private val repository: OrderListRepository,
) {
    suspend operator fun invoke(
        clientId: String,
        status: String? = null,
    ): OrderListData = repository.getOrderList(clientId, status)
}
