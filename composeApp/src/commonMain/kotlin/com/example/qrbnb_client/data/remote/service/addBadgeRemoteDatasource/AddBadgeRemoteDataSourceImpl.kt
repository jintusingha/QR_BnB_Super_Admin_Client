package com.example.qrbnb_client.data.remote.service.addBadgeRemoteDatasource

import com.example.qrbnb_client.data.remote.model.addBadgeRequestDto.AddBadgeRequestDto
import com.example.qrbnb_client.data.remote.model.addBadgeRequestDto.AddBadgeResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AddBadgeRemoteDataSourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient
) : AddBadgeRemoteDataSource {

    override suspend fun addBadge(request: AddBadgeRequestDto): AddBadgeResponseDto {
        try {
            val url = "$baseUrl/client/menu/config/badges"
            println("Sending Add Badge request to: $url")
            println("REQUEST BODY ➜ $request")

            val response: HttpResponse =
                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }

            println("Add Badge response status: ${response.status}")

            val rawText = response.bodyAsText()
            println("RAW BADGE RESPONSE:\n$rawText")

            val body = response.body<AddBadgeResponseDto>()
            println("Badge added successfully with success=${body.success}")

            return body

        } catch (e: Exception) {
            println("Error adding badge: ${e.message}")
            throw e
        }
    }
}
