package com.example.qrbnb_client.domain.entity.AddItemsResponse

data class DynamicFormEntity(
    val formId: String,
    val title: String,
    val fields: List<FormFieldEntity>,
    val actions: List<FormActionEntity>
)

data class FormFieldEntity(
    val id: String,
    val label: String,
    val type: String,
    val uploadEndpoint: String? = null,
    val required: Boolean = false,
    val defaultValue: Boolean? = null,
    val note: String? = null,
    val optionsEndpoint: String? = null,
    val fields: List<FormFieldEntity>? = null,
    var value: Any? = null
)

data class FieldOptionEntity(
    val label: String,
    val value: String,
    val price: Int? = null,
    val selectionType: String? = null,
    val options: List<FieldOptionEntity>? = null
)

data class FormActionEntity(
    val id: String,
    val type: String,
    val method: String?,
    val endpoint: String?
)
