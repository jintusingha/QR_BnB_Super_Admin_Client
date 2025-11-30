package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.orderListResponse.OrderListData

interface OrderListRepository {
    suspend fun getOrderList(
        clientId: String,
        status: String? = null,
    ): OrderListData
}
