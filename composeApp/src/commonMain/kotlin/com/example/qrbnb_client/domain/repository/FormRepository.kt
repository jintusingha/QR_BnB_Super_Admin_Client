package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.AddItemsResponse.DynamicFormEntity

interface FormRepository {
    suspend fun getAddItemForm(): DynamicFormEntity
}