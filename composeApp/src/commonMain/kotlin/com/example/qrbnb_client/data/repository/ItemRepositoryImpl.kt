package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.remote.service.addItemFormSubmitDataSource.ItemDataSource
import com.example.qrbnb_client.domain.repository.ItemRepository

class ItemRepositoryImpl(
    private val dataSource: ItemDataSource
) : ItemRepository {

    override suspend fun submitItem(
        formId: String,
        values: Map<String, Any?>
    ) {
        dataSource.submitItem(formId, values)
    }
}