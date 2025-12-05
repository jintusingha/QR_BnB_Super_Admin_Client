package com.example.qrbnb_client.data.remote.model.stockItemDto

data class StockItemDto(
    val id:Int,
    val name:String,
    val price:Double,
    val quantity:Int,
    val unit:String,
    val category:String,
    val imageUrl:String
)

enum class StockCategory {
    FOOD,
    BEVERAGES,
    SUPPLIES
}