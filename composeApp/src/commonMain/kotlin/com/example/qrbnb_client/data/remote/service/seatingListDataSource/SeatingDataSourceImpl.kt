package com.example.qrbnb_client.data.remote.service.seatingListDataSource

import com.example.qrbnb_client.data.remote.model.seatingListResponseDto.SeatingListResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class SeatingDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : SeatingDataSource {

    override suspend fun getSeatingList(): SeatingListResponseDto {
        val url = "$baseUrl/client/seating-areas"

        println("Requesting Seating List from: $url")

        val response = httpClient.get(url)
        println("Response Status: ${response.status}")

        val raw = response.bodyAsText()
        println("Raw Response Body: $raw")

        return try {
            val dto = response.body<SeatingListResponseDto>()
            println("Parsed DTO: $dto")
            dto
        } catch (e: Exception) {
            println("Error parsing DTO: ${e.message}")
            throw e
        }
    }
}