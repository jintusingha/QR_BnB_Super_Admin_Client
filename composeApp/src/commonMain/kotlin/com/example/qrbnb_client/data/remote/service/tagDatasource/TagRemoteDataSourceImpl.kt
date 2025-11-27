package com.example.qrbnb_client.data.remote.service.tagDatasource

import com.example.qrbnb_client.data.remote.model.tagRequest.CreateTagRequestDto
import com.example.qrbnb_client.data.remote.model.tagRequest.CreateTagResponseDto
import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseDto
import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseWrapperDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType

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

    override suspend fun createTag(request: CreateTagRequestDto): CreateTagResponseDto {
        try {
            val url = "$baseUrl/client/menu/config/tags"
            println("Sending Create Tag request to: $url with body=$request")

            val response: HttpResponse = httpClient.post(url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            println("Create Tag response status: ${response.status}")

            val raw = response.bodyAsText()
            println("RAW CREATE TAG RESPONSE:\n$raw")

            return response.body()

        } catch (e: Exception) {
            println("Error creating tag: ${e.message}")
            throw e
        }
    }

}
