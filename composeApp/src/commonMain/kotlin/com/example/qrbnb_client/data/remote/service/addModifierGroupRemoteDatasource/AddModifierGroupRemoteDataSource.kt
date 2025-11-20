package com.example.qrbnb_client.data.remote.service.addModifierGroupRemoteDatasource

import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupRequestDto
import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupResponseDto

interface AddModifierGroupRemoteDataSource{
    suspend fun addModifierGroup(request: AddModifierGroupRequestDto): AddModifierGroupResponseDto
}