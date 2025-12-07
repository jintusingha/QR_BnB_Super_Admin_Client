package com.example.qrbnb_client.data.remote.service.editTagRemoteDatasource

import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagRequestDto
import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

class EditTagRemoteDataSourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : EditTagRemoteDataSource {
    override suspend fun editTag(
        request: EditTagRequestDto,
        tagId: String,
    ): EditTagResponseDto {
        try {
            val url = "$baseUrl/client/menu/config/tags/$tagId"

            val response =
                httpClient.patch(url) {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }

            val raw = response.bodyAsText()

            return response.body()
        } catch (e: Exception) {
            throw e
        }
    }
}
