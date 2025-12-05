package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.data.remote.model.stockItemDto.StockCategory
import com.example.qrbnb_client.domain.entity.stockItemEntity.StockItemEntity

data class StockUiState (
    val items:List<StockItemEntity> = emptyList(),
    val isLoading:Boolean=false,
    val error:String?=null,
    val selectedCategory: StockCategory?=null
)