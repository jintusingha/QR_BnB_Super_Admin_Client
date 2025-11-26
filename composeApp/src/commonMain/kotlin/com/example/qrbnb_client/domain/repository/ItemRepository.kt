package com.example.qrbnb_client.domain.repository

interface ItemRepository {
    suspend fun submitItem(
        formId: String,
        values: Map<String, Any?>
    )
}