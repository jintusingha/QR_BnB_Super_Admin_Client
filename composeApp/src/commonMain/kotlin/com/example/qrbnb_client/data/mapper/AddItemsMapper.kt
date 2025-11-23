package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.AddItemsDto.DynamicFormDto
import com.example.qrbnb_client.data.remote.model.AddItemsDto.FieldOptionDto
import com.example.qrbnb_client.data.remote.model.AddItemsDto.FormActionDto
import com.example.qrbnb_client.data.remote.model.AddItemsDto.FormFieldDto
import com.example.qrbnb_client.domain.entity.AddItemsResponse.DynamicFormEntity
import com.example.qrbnb_client.domain.entity.AddItemsResponse.FieldOptionEntity
import com.example.qrbnb_client.domain.entity.AddItemsResponse.FormActionEntity
import com.example.qrbnb_client.domain.entity.AddItemsResponse.FormFieldEntity

fun DynamicFormDto.toEntity(): DynamicFormEntity =
    DynamicFormEntity(
        formId = data.formId,
        title = data.title,
        fields = data.fields.map { it.toEntity() },
        actions = data.actions.map { it.toEntity() },
    )

fun FormFieldDto.toEntity(): FormFieldEntity {
    return FormFieldEntity(
        id = id,
        label = label ?: "",
        type = type,
        uploadEndpoint = uploadEndpoint,
        required = required ?: false,
        defaultValue = defaultValue,
        note = note,
        optionsEndpoint = optionsEndpoint,
        options = options?.map { it.toEntity() },   // ← NEW
        fields = fields?.map { it.toEntity() },
        value = null
    )
}

fun FieldOptionDto.toEntity(): FieldOptionEntity {
    return FieldOptionEntity(
        label = label,
        value = value,
        price = price,
        selectionType = selectionType,
        options = options?.map { it.toEntity() }   // nested options
    )
}
fun FormActionDto.toEntity(): FormActionEntity =
    FormActionEntity(
        id = id,
        type = type,
        method = method,
        endpoint = endpoint,
    )
