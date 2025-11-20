package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.addModifierGroup.ModifierGroupEntity
import com.example.qrbnb_client.domain.entity.modifierGroup.ModifierGroupResponseEntity

sealed class ModifierGroupsUiState {
    object Loading : ModifierGroupsUiState()
    data class Success(val items: List<ModifierGroupResponseEntity>) : ModifierGroupsUiState()
    data class Error(val message: String) : ModifierGroupsUiState()
}