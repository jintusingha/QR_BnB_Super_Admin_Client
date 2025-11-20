package com.example.qrbnb_client.data.remote.service.addModifierGroupRemoteDatasource

import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupRequestDto
import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupResponseDto
import com.example.qrbnb_client.data.remote.model.addModifierGroup.ModifierGroupDataDto

class AddModifierGroupRemoteDataSourceImpl : AddModifierGroupRemoteDataSource{
    override suspend fun addModifierGroup(request: AddModifierGroupRequestDto): AddModifierGroupResponseDto {
        return AddModifierGroupResponseDto(
            success = true,
            message = "Modifier group saved successfully",
            data = ModifierGroupDataDto(groupId = "group_1 created")
        )
    }

}