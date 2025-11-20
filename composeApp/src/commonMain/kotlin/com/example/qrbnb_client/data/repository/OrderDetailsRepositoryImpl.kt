package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomain
import com.example.qrbnb_client.data.remote.service.orderDetailsDatasource.OrderDetailsRemoteDatasource
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderDetailsEntity
import com.example.qrbnb_client.domain.repository.OrderDetailsRepository

class OrderDetailsRepositoryImpl(
    private val datasource: OrderDetailsRemoteDatasource,
) : OrderDetailsRepository {
    override suspend fun getOrderDetails(orderId: String): OrderDetailsEntity = datasource.getOrderDetails(orderId).data.toDomain()
}
