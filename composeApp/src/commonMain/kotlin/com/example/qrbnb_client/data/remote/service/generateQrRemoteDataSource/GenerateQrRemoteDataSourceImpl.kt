package com.example.qrbnb_client.data.remote.service.generateQrRemoteDataSource

import com.example.qrbnb_client.data.remote.model.generateQrResponseDto.GenerateQrResponseDto
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

class GenerateQrRemoteDataSourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : GenerateQrRemoteDataSource {
    override suspend fun generateQr(seatingId: String): GenerateQrResponseDto =
        try {
            val url = "$baseUrl/client/seating/$seatingId/qr/generate"

            println("Generating QR... $url")

            val response =
                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                }

            println("QR response status: ${response.status}")
            println("RAW RESPONSE:\n${response.bodyAsText()}")

            response.body()
        } catch (e: Exception) {
            println("Error generating QR: ${e.message}")
            throw e
        }
}
