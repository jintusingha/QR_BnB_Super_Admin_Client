package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.orderResponseDto.OrderItemDto
import com.example.qrbnb_client.data.remote.model.orderResponseDto.OrderResponseDto
import com.example.qrbnb_client.domain.entity.orderReponse.OrderEntity

fun OrderItemDto.toDomain(): OrderEntity =
    OrderEntity(
        id = id,
        title = title,
        subtitle = subtitle,
        description = description,
        price = price,
        status = status,
        imageUrl = imageUrl,
    )

fun List<OrderItemDto>.toDomainList(): List<OrderEntity> = map { it.toDomain() }
