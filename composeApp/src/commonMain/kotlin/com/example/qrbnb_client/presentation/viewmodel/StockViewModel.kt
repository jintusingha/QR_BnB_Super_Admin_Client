package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.data.remote.model.stockItemDto.StockCategory
import com.example.qrbnb_client.domain.entity.stockItemEntity.StockItemEntity
import com.example.qrbnb_client.domain.usecase.GetStockItemsUseCase
import com.example.qrbnb_client.presentation.state.StockUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockViewModel(
    private val getStockItemsUseCase: GetStockItemsUseCase,
) : ViewModel() {
    private var originalList: List<StockItemEntity> = emptyList()
    private val _currentList = MutableStateFlow<List<StockItemEntity>>(emptyList())
    private val _selectedCategory = MutableStateFlow<StockCategory?>(null)

    private val _isSaveButtonVisible = MutableStateFlow(false)
    val isSaveButtonVisible = _isSaveButtonVisible.asStateFlow()

    private val _uiState = MutableStateFlow(StockUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadStockItems()
    }

    private fun loadStockItems() {
        viewModelScope.launch {
            try {
                _uiState.value = uiState.value.copy(isLoading = true)

                val items = getStockItemsUseCase()
                originalList = items
                _currentList.value = items

                applyFiltering()
                updateSaveButtonState()
            } catch (e: Exception) {
                _uiState.value = uiState.value.copy(error = e.message)
            } finally {
                _uiState.value = uiState.value.copy(isLoading = false)
            }
        }
    }

    fun onCategorySelected(category: StockCategory?) {
        _selectedCategory.value = category
        applyFiltering()
    }

    fun updateItemQuantity(
        itemId: Int,
        newQty: Int,
    ) {
        _currentList.value =
            _currentList.value.map { item ->
                if (item.id == itemId) item.copy(quantity = newQty) else item
            }

        applyFiltering()
        updateSaveButtonState()
    }

    private fun updateSaveButtonState() {
        val originalMap = originalList.associateBy { it.id }

        val modified =
            _currentList.value.any { current ->
                val original = originalMap[current.id]
                original != null && current.quantity != original.quantity
            }

        _isSaveButtonVisible.value = modified
    }

    private fun applyFiltering() {
        val category = _selectedCategory.value
        val items = _currentList.value

        val filtered = if (category == null) items else items.filter { it.category == category }

        _uiState.value =
            uiState.value.copy(
                items = filtered,
                selectedCategory = category,
            )
    }
}
