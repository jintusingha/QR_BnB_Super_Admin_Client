package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toEntity
import com.example.qrbnb_client.data.remote.service.AddItemsRemoteDatasource.FormDataSource
import com.example.qrbnb_client.domain.entity.AddItemsResponse.DynamicFormEntity
import com.example.qrbnb_client.domain.entity.AddItemsResponse.FieldOptionEntity
import com.example.qrbnb_client.domain.entity.AddItemsResponse.FormFieldEntity
import com.example.qrbnb_client.domain.repository.FormRepository

class FormRepositoryImpl(
    private val dataSource: FormDataSource,
) : FormRepository {
    override suspend fun getAddItemForm(): DynamicFormEntity {
        val dto = dataSource.getAddItemForm()
        return dto.toEntity()


    }
}

