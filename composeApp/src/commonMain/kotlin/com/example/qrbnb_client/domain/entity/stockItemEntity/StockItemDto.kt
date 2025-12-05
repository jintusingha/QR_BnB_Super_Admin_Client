package com.example.qrbnb_client.domain.entity.stockItemEntity

import com.example.qrbnb_client.data.remote.model.stockItemDto.StockCategory

data class StockItemEntity(
    val id:Int,
    val name:String,
    val price:Double,
    val quantity:Int,
    val unit:String,
    val category: StockCategory,
    val imageUrl:String
)