package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.stockItemDto.StockCategory
import com.example.qrbnb_client.data.remote.model.stockItemDto.StockItemDto

import com.example.qrbnb_client.domain.entity.stockItemEntity.StockItemEntity

fun StockItemDto.toEntity(): StockItemEntity {
    return StockItemEntity(
        id=id,
        name=name,
        price=price,
        quantity=quantity,
        unit=unit,
        category= StockCategory.valueOf(category.uppercase()),
        imageUrl=imageUrl
    )

}