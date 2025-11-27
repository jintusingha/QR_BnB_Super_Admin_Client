package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.CreateTagUseCase
import com.example.qrbnb_client.domain.usecase.GetTagsUseCase
import com.example.qrbnb_client.presentation.state.TagUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TagViewModel(
    private val getTagsUseCase: GetTagsUseCase,
    private val createTagUseCase: CreateTagUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<TagUiState>(TagUiState.Loading)
    val uiState: StateFlow<TagUiState> = _uiState

    init {
        loadTags()
    }

    private fun loadTags() {
        viewModelScope.launch {
            try {
                val tags = getTagsUseCase()
                _uiState.value = TagUiState.Success(tags)
            } catch (e: Exception) {
                _uiState.value = TagUiState.Error(e.message ?: "Failed to load tags")
            }
        }
    }
    fun createTag(name: String) {
        viewModelScope.launch {
            try {
                createTagUseCase(name)
                loadTags()
            } catch (e: Exception) {
                _uiState.value = TagUiState.Error(e.message ?: "Failed to create tag")
            }
        }
    }
}
