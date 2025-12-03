package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.ordersByDateDto.OrderItemDto
import com.example.qrbnb_client.data.remote.model.ordersByDateDto.OrdersByDateResponseDto
import com.example.qrbnb_client.domain.entity.ordersByDateEntity.OrderItemEntity
import com.example.qrbnb_client.domain.entity.ordersByDateEntity.OrdersByDateEntity

fun OrdersByDateResponseDto.toEntity(): OrdersByDateEntity {
    return OrdersByDateEntity(
        totalOrders = data.totalOrders,
        totalAmount = data.totalAmount,
        orders = data.orders.map { it.toEntity() }
    )
}

fun OrderItemDto.toEntity(): OrderItemEntity {
    return OrderItemEntity(
        orderId = orderId,
        orderNumber = orderNumber,
        seatingArea = seatingArea,
        total = total,
        createdAt = createdAt
    )
}