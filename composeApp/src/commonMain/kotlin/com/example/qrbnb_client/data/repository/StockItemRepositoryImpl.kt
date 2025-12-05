package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toEntity
import com.example.qrbnb_client.data.remote.service.stockItemDataSource.StockItemDataSource
import com.example.qrbnb_client.data.remote.service.stockItemDataSource.StockItemDataSourceImpl
import com.example.qrbnb_client.domain.entity.stockItemEntity.StockItemEntity
import com.example.qrbnb_client.domain.repository.StockItemRepository

class StockItemRepositoryImpl (
    private val dataSource: StockItemDataSource
): StockItemRepository{
    override suspend fun getStockItems(): List<StockItemEntity> {
        return dataSource.getStockItems().map { it.toEntity() }
    }
}