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
        var entity = dto.toEntity()

        val fixedFields =
            entity.fields
                .map { field ->

                    when (field.id) {
                        "category" -> {
                            if (field.options == null || field.options!!.isEmpty()) {
                                field.copy(options = dummyCategoryOptions())
                            } else {
                                field
                            }
                        }

                        "tags" -> {
                            if (field.options == null || field.options!!.isEmpty()) {
                                field.copy(options = dummyTags())
                            } else {
                                field
                            }
                        }

                        "badge" -> {
                            if (field.options == null || field.options!!.isEmpty()) {
                                field.copy(options = dummyBadges())
                            } else {
                                field
                            }
                        }

                        "variants" -> {
                            if (field.options == null) {
                                field.copy(options = dummyVariants())
                            } else {
                                field
                            }
                        }

                        "modifierGroups" -> {
                            if (field.options == null) {
                                field.copy(options = dummyModifierGroups())
                            } else {
                                field
                            }
                        }

                        else -> field
                    }
                }.toMutableList()

        if (fixedFields.none { it.id == "badge" }) {
            val badgeField =
                FormFieldEntity(
                    id = "badge",
                    label = "Badge",
                    type = "select",
                    options = dummyBadges(),
                )

            val catIndex = fixedFields.indexOfFirst { it.id == "category" }
            if (catIndex != -1) {
                fixedFields.add(catIndex + 1, badgeField)
            } else {
                fixedFields.add(badgeField)
            }
        }

        if (fixedFields.none { it.id == "modifierGroups" }) {
            val modifierGroupField =
                FormFieldEntity(
                    id = "modifierGroups",
                    label = "Modifier Groups",
                    type = "multiselect",
                    options = dummyModifierGroups(),
                )

            val variantIndex = fixedFields.indexOfFirst { it.id == "variants" }
            if (variantIndex != -1) {
                fixedFields.add(variantIndex + 1, modifierGroupField)
            } else {
                fixedFields.add(modifierGroupField)
            }
        }

        return entity.copy(fields = fixedFields)
    }
}

fun dummyCategoryOptions() =
    listOf(
        FieldOptionEntity("Starter", "starter"),
        FieldOptionEntity("Main Course", "main"),
        FieldOptionEntity("Dessert", "dessert"),
    )

fun dummyTags() =
    listOf(
        FieldOptionEntity("spicy", "spicy"),
        FieldOptionEntity("vegan", "vegan"),
    )

fun dummyVariants() =
    listOf(
        FieldOptionEntity(
            label = "Size",
            value = "size",
            options =
                listOf(
                    FieldOptionEntity("Small", "small", 0),
                    FieldOptionEntity("Large", "large", 100),
                ),
        ),
    )

fun dummyModifierGroups() =
    listOf(
        FieldOptionEntity(
            label = "Add-ons",
            value = "addons",
            selectionType = "multiple",
            options =
                listOf(
                    FieldOptionEntity("Extra Cheese", "cheese", 30),
                    FieldOptionEntity("Olives", "olives", 20),
                ),
        ),
    )

fun dummyBadges() =
    listOf(
        FieldOptionEntity("Spicy", "6194216e-8d22-4f4a-b80e-51381f259687"),
        FieldOptionEntity("New", "ec7cf4cf-f489-40ab-b232-78a2380b2130"),
        FieldOptionEntity("Chef Special", "bd4910d8-65c8-4ef4-8b19-855456d335ff"),
        FieldOptionEntity("Popular", "05220233-42cc-4cf3-938b-36f9ae04fc67"),
    )
