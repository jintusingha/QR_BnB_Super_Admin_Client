package com.example.qrbnb_client.data.remote.service.qrCodeDataSource

import com.example.qrbnb_client.data.remote.model.qrCodeDto.QrCodesResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class QrCodesDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : QrCodesDataSource {

    override suspend fun getQrCodes(): QrCodesResponseDto {
        val url = "$baseUrl/client/seating/qr-codes"

        println("Requesting QR Codes from: $url")

        val response = httpClient.get(url)
        println("Response Status: ${response.status}")

        val raw = response.bodyAsText()
        println("Raw Response Body: $raw")

        return try {
            val dto = response.body<QrCodesResponseDto>()
            println("Parsed DTO: $dto")
            dto
        } catch (e: Exception) {
            println(" Error parsing DTO: ${e.message}")
            throw e
        }
    }
}
