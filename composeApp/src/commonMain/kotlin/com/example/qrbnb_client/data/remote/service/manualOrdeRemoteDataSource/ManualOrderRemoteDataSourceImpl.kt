package com.example.qrbnb_client.data.remote.service.manualOrdeRemoteDataSource

import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manualOrderResponseDto.ManualOrderResponseDto
import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manulOrderRequestDto.ManualOrderRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ManualOrderRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : ManualOrderRemoteDataSource {
    override suspend fun createManualOrder(request: ManualOrderRequestDto): ManualOrderResponseDto {
        val url = "$baseUrl/client/manual-order"

        println(" Sending Manual Order Request to: $url")
        println(" Request Body: $request")

        val response =
            httpClient.post(url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

        println(" Response Status: ${response.status}")

        val rawBody = response.bodyAsText()
        println("Raw Response Body: $rawBody")

        return try {
            val dto = response.body<ManualOrderResponseDto>()
            println("Parsed DTO: $dto")
            dto
        } catch (e: Exception) {
            println(" Error parsing ManualOrderResponseDto: ${e.message}")
            throw e
        }
    }
}
