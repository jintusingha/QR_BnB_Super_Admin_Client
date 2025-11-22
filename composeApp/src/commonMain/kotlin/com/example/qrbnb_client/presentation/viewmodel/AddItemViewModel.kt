package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.data.remote.service.fake.*
import com.example.qrbnb_client.domain.entity.addItem.*
import com.example.qrbnb_client.domain.usecase.AddItemUseCase
import com.example.qrbnb_client.presentation.state.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddItemViewModel(
    private val addItemUseCase: AddItemUseCase,
) : ViewModel() {
    private val _categories = MutableStateFlow(FakeCategoryDataSource.getCategories())
    val categories: StateFlow<List<FakeCategoryDto>> = _categories

    private val _tags = MutableStateFlow(FakeTagDataSource.getTags())
    val tags: StateFlow<List<FakeTagDto>> = _tags

    private val _badges = MutableStateFlow(FakeBadgeDataSource.getBadges())
    val badges: StateFlow<List<FakeBadgeDto>> = _badges

    private val _modifierGroups = MutableStateFlow(FakeModifierGroupDataSource.getModifierGroups())
    val modifierGroups: StateFlow<List<FakeModifierGroupDto>> = _modifierGroups

    private val _variants = MutableStateFlow(FakeVariantDataSource.getVariants())
    val variants: StateFlow<List<FakeVariantDto>> = _variants

    private val _uiState =
        MutableStateFlow<AddItemUiState>(
            AddItemUiState.Data(AddItemFormData()),
        )
    val uiState: StateFlow<AddItemUiState> = _uiState

    fun onNameChange(value: String) = updateForm { it.copy(name = value) }

    fun onPriceChange(value: String) = updateForm { it.copy(price = value) }

    fun onDescriptionChange(value: String) = updateForm { it.copy(description = value) }

    fun onCategorySelect(id: String) = updateForm { it.copy(categoryId = id) }

    fun toggleAvailable() = updateForm { it.copy(available = !it.available) }

    fun onBadgesSelected(list: List<String>) = updateForm { it.copy(selectedBadges = list) }

    fun onTagsSelected(list: List<String>) = updateForm { it.copy(selectedTags = list) }

    fun onModifierGroupsSelected(list: List<String>) = updateForm { it.copy(selectedModifierGroups = list) }

    fun addVariant(
        name: String,
        price: String?,
    ) {
        updateForm {
            it.copy(
                variants = it.variants + VariantUi(name, price),
            )
        }
    }



    fun saveItem() {
        val currentState = _uiState.value
        if (currentState !is AddItemUiState.Data) return

        val form = currentState.form

        viewModelScope.launch {
            _uiState.value = AddItemUiState.Loading

            val entity =
                AddItemEntity(
                    name = form.name,
                    price = form.price.toDoubleOrNull() ?: 0.0,
                    description = form.description,
                    categoryId = form.categoryId,
                    imageUrl = form.imageUrl,
                    available = form.available,
                    badges = form.selectedBadges,
                    tags = form.selectedTags,
                    variants =
                        form.variants.map {
                            VariantEntity(
                                name = it.name,
                                price = it.price?.toDoubleOrNull(),
                            )
                        },
                    modifierGroupIds = form.selectedModifierGroups,
                )

            try {
                val result = addItemUseCase(entity)

                if (result.success) {
                    println("ITEM ADDED SUCCESSFULLY  ${result.message}")

                    _uiState.value = AddItemUiState.Success("Item added successfully")

                    kotlinx.coroutines.delay(300)
                    _uiState.value = AddItemUiState.Data(AddItemFormData())
                } else {
                    _uiState.value = AddItemUiState.Error(result.message)

                    kotlinx.coroutines.delay(300)
                    _uiState.value = AddItemUiState.Data(form)
                }
            } catch (e: Exception) {
                _uiState.value = AddItemUiState.Error(e.message ?: "Error occurred")

                kotlinx.coroutines.delay(300)
                _uiState.value = AddItemUiState.Data(form)
            }
        }
    }

    private fun updateForm(block: (AddItemFormData) -> AddItemFormData) {
        val current = _uiState.value
        if (current is AddItemUiState.Data) {
            _uiState.value = AddItemUiState.Data(block(current.form))
        }
    }
}
