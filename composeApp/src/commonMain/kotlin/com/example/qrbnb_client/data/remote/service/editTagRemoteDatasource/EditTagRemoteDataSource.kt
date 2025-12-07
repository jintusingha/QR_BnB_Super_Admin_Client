package com.example.qrbnb_client.data.remote.service.editTagRemoteDatasource

import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagRequestDto
import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagResponseDto

interface EditTagRemoteDataSource {
    suspend fun editTag(request: EditTagRequestDto, tagId: String): EditTagResponseDto
}
