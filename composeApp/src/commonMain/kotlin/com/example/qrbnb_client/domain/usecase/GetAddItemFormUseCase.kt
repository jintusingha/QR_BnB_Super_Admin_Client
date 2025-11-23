package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.AddItemsResponse.DynamicFormEntity
import com.example.qrbnb_client.domain.repository.FormRepository

class GetAddItemFormUseCase(
    private val repository: FormRepository
) {
    suspend operator fun invoke(): DynamicFormEntity {
        return repository.getAddItemForm()
    }
}