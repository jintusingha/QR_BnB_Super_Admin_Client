package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomainList
import com.example.qrbnb_client.data.remote.service.orderRemoteDatasource.OrderRemoteDatasource
import com.example.qrbnb_client.domain.entity.orderReponse.OrderEntity
import com.example.qrbnb_client.domain.repository.OrdersRepository

class OrdersRepositoryImpl(
    private val datasource: OrderRemoteDatasource,
) : OrdersRepository {
    override suspend fun getOrders(): List<OrderEntity> = datasource.getOrder().data.toDomainList()
}
