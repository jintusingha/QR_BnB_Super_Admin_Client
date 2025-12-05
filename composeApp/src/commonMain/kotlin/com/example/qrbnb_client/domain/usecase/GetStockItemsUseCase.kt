package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.stockItemEntity.StockItemEntity
import com.example.qrbnb_client.domain.repository.StockItemRepository

class GetStockItemsUseCase (
    private val repository: StockItemRepository
){
    suspend operator fun invoke():List<StockItemEntity>{
        return repository.getStockItems()
    }
}