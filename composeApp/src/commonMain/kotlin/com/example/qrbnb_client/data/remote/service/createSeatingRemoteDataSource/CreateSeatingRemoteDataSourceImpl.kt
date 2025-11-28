package com.example.qrbnb_client.data.remote.service.createSeatingRemoteDataSource

import com.example.qrbnb_client.data.remote.model.createSeatingDto.CreateSeatingRequestDto
import com.example.qrbnb_client.data.remote.model.createSeatingDto.CreateSeatingResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CreateSeatingRemoteDataSourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient
) : CreateSeatingRemoteDataSource {

    override suspend fun createSeatingArea(request: CreateSeatingRequestDto): CreateSeatingResponseDto {
        return try {
            val url = "$baseUrl/client/seating-areas"
            println("Creating Seating Area: POST $url")
            println("REQUEST BODY  $request")

            val response: HttpResponse =
                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }

            println("Create Seating response status: ${response.status}")
            println("RAW RESPONSE: ${response.bodyAsText()}")

            response.body()
        } catch (e: Exception) {
            println("Error creating seating: ${e.message}")
            throw e
        }
    }
}