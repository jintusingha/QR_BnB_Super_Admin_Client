package com.example.qrbnb_client.data.remote.service.editTagRemoteDatasource

import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagRequestDto
import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagResponseDto
import kotlinx.coroutines.delay

class EditTagRemoteDataSourceImpl : EditTagRemoteDataSource {

    override suspend fun editTag(request: EditTagRequestDto): EditTagResponseDto {
        delay(1000)

        println("FAKE API:  id=${request.tagId}, newName=${request.newName}")

        return EditTagResponseDto(
            success = true,
            message = "Tag updated successfully!"
        )
    }
}
