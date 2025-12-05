package com.example.qrbnb_client.data.remote.service.stockItemDataSource

import com.example.qrbnb_client.data.remote.model.stockItemDto.StockItemDto

interface StockItemDataSource {
    suspend fun getStockItems():List<StockItemDto>
}