package com.example.qrbnb_client.data.remote.service.AddItemsRemoteDatasource

import com.example.qrbnb_client.data.remote.model.AddItemsDto.DynamicFormDto

interface FormDataSource {
    suspend fun getAddItemForm(): DynamicFormDto
}