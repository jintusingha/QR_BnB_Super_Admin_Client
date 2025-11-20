package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.CustomerDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrderDetailsDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrderItemsDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrdersDetailsResponseDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.TimeLineStepDto
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.CustomerEntity
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderDetailsEntity
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderItemEntity
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.TimelineStepEntity

fun OrdersDetailsResponseDto.toDomain(): OrderDetailsEntity = data.toDomain()

fun OrderDetailsDto.toDomain(): OrderDetailsEntity =
    OrderDetailsEntity(
        orderId = orderId,
        placedAt = placedAt,
        customer = customer.toDomain(),
        items = items.map { it.toDomain() },
        subtotal = subtotal,
        tax = tax,
        total = total,
        timeline = timeline.map { it.toDomain() },
        status = status,
    )

fun CustomerDto.toDomain(): CustomerEntity =
    CustomerEntity(
        name = name,
        phone = phone,
        table = table,
        avatar = avatar,
    )

fun OrderItemsDto.toDomain(): OrderItemEntity =
    OrderItemEntity(
        name = name,
        quantity = quantity,
        price = price,
    )

fun TimeLineStepDto.toDomain(): TimelineStepEntity =
    TimelineStepEntity(
        title = title,
        time = time,
        completed = completed,
    )
