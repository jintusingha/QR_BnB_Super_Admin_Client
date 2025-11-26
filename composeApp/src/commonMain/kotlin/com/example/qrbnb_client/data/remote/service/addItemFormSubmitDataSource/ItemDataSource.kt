package com.example.qrbnb_client.data.remote.service.addItemFormSubmitDataSource

interface ItemDataSource {
    suspend fun submitItem(
        formId: String,
        values: Map<String, Any?>
    )
}