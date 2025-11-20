package com.example.qrbnb_client.data.remote.service.addBadgeRemoteDatasource

import com.example.qrbnb_client.data.remote.model.addBadgeRequestDto.AddBadgeRequestDto
import com.example.qrbnb_client.data.remote.model.addBadgeRequestDto.AddBadgeResponseDto

class AddBadgeRemoteDataSourceImpl : AddBadgeRemoteDataSource {
    override suspend fun addBadge(request: AddBadgeRequestDto): AddBadgeResponseDto {
        println("Saving Badge: $request")

        return AddBadgeResponseDto(
            success = true,
            message = "Badge added successfully!"
        )
    }
}