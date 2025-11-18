package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.tagResponse.TagEntity

sealed class TagUiState {
    object Loading : TagUiState()
    data class Success(val tags: List<TagEntity>) : TagUiState()
    data class Error(val message:String): TagUiState()
}