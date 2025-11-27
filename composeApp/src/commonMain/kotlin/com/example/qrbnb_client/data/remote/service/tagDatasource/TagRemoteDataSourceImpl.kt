package com.example.qrbnb_client.data.remote.service.tagDatasource

import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseDto
import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseWrapperDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

class TagRemoteDataSourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient
) : TagRemoteDataSource {

    override suspend fun getTags(): TagResponseWrapperDto {
        try {
            val url = "$baseUrl/client/menu/config/tags"
            println("Fetching Tags from: $url")

            val response: HttpResponse =
                httpClient.get(url)

            println("Tag response status: ${response.status}")

            val raw = response.bodyAsText()
            println("RAW TAG RESPONSE:\n$raw")

            val body = response.body<TagResponseWrapperDto>()
            println("Tags fetched successfully: count=${body.data.size}")

            return body

        } catch (e: Exception) {
            println("Error fetching tags: ${e.message}")
            throw e
        }
    }
}
