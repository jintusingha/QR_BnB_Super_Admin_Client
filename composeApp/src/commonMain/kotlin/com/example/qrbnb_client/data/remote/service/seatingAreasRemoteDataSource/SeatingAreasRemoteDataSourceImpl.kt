package com.example.qrbnb_client.data.remote.service.seatingAreasRemoteDataSource

import com.example.qrbnb_client.data.remote.model.seatingAreasDto.SeatingAreasResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

class SeatingAreasRemoteDataSourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : SeatingAreasRemoteDataSource {
    override suspend fun fetchSeatingAreas(): SeatingAreasResponseDto =
        try {
            val url = "$baseUrl/client/seating-areas"
            println("Fetching seating areas:$url")
            val response: HttpResponse = httpClient.get(url)
            println("Seating Areas response status: ${response.status}")
            val raw = response.bodyAsText()
            println("RAW SEATING AREAS RESPONSE:\n$raw")
            response.body()
        } catch (e: Exception) {
            println("Error fetching seating areas: ${e.message}")
            throw e
        }
}
