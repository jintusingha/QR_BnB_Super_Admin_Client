package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.CustomerDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrderDetailsDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrderItemDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrdersDetailsResponseDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.TimelineDto
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.CustomerEntity
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderDetailsEntity
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderItemEntity
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.TimelineStepEntity

fun OrdersDetailsResponseDto.toDomain(): OrderDetailsEntity = data.toDomain()

fun OrderDetailsDto.toDomain() =
    OrderDetailsEntity(
        orderId = orderId,
        placedAt = createdAt,
        customer = customer.toDomain(),
        items = items.map { it.toDomain() },
        subtotal = summary.subtotal,
        tax = summary.taxes,
        total = summary.total,
        timeline = timeline.map { it.toDomain() },
        status = timeline.lastOrNull()?.status ?: "Unknown",
    )

fun CustomerDto.toDomain() =
    CustomerEntity(
        name = name,
        phone = phone,
        table = seatingArea.name,
        avatar = "",
    )

fun OrderItemDto.toDomain() =
    OrderItemEntity(
        name = name,
        quantity = quantity,
        price = priceEach,
    )

fun TimelineDto.toDomain() =
    TimelineStepEntity(
        title = status,
        time = time,
        completed = false,
    )
