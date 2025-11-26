package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.repository.ItemRepository

class SubmitItemUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(
        formId: String,
        values: Map<String, Any?>
    ) {
        repository.submitItem(formId, values)
    }
}