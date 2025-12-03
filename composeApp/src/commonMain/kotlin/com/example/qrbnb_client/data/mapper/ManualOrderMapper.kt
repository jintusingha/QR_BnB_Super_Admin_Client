package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manualOrderResponseDto.ManualOrderResultDto
import com.example.qrbnb_client.domain.entity.manualOrderEntity.ManualOrderEntity

fun ManualOrderResultDto.toEntity(): ManualOrderEntity {
    return ManualOrderEntity(
        orderId = orderId,
        orderNumber = orderNumber,
        total = total,
        status = status
    )
}