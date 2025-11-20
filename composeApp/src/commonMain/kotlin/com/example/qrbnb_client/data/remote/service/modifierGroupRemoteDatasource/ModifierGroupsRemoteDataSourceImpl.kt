package com.example.qrbnb_client.data.remote.service.modifierGroupRemoteDatasource

import com.example.qrbnb_client.data.remote.model.modifierGroupDto.ModifierGroupDto
import com.example.qrbnb_client.data.remote.model.modifierGroupDto.ModifierGroupListResponseDto
import kotlinx.coroutines.delay

class ModifierGroupsRemoteDataSourceImpl : ModifierGroupsRemoteDataSource {

    override suspend fun getModifierGroups(): ModifierGroupListResponseDto {
        delay(800)

        return ModifierGroupListResponseDto(
            success = true,
            data = listOf(
                ModifierGroupDto(id = "1", name = "Size", itemCount = 3),
                ModifierGroupDto(id = "2", name = "Toppings", itemCount = 5),
                ModifierGroupDto(id = "3", name = "Add-ons", itemCount = 2)
            )
        )
    }
}