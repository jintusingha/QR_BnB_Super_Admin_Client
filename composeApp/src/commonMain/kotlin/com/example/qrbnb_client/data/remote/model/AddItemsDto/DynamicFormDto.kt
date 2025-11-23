package com.example.qrbnb_client.data.remote.model.AddItemsDto

import kotlinx.serialization.Serializable

@Serializable
data class DynamicFormDto(
    val success: Boolean,
    val message: String,
    val data: FormDataDto
)

@Serializable
data class FormDataDto(
    val formId: String,
    val title: String,
    val fields: List<FormFieldDto>,
    val actions: List<FormActionDto>
)

@Serializable
data class FormFieldDto(
    val id: String,
    val label: String? = null,
    val type: String,
    val uploadEndpoint: String? = null,
    val required: Boolean? = null,
    val defaultValue: Boolean? = null,
    val note: String? = null,
    val optionsEndpoint: String? = null,
    val fields: List<FormFieldDto>? = null

)

@Serializable
data class FieldOptionDto(
    val label: String,
    val value: String,
    val price: Int? = null,
    val selectionType: String? = null,
    val options: List<FieldOptionDto>? = null
)

@Serializable
data class FormActionDto(
    val id: String,
    val type: String,
    val method: String? = null,
    val endpoint: String? = null
)
