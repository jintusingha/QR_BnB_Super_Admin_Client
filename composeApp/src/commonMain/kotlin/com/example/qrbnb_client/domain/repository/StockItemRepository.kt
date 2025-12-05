package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.stockItemEntity.StockItemEntity

interface StockItemRepository{
    suspend fun getStockItems():List<StockItemEntity>
}