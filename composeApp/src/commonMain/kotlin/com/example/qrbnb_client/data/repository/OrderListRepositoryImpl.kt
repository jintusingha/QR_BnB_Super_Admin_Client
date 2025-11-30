package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomain
import com.example.qrbnb_client.data.remote.service.orderListDataSource.OrderListDataSource
import com.example.qrbnb_client.domain.entity.orderListResponse.OrderListData
import com.example.qrbnb_client.domain.repository.OrderListRepository

class OrderListRepositoryImpl (private val datasource: OrderListDataSource): OrderListRepository {
    override suspend fun getOrderList(
        clientId: String,
        status: String?
    ): OrderListData {
        val response=datasource.getOrderListData(clientId,status)
        return response.data.toDomain()

    }

}