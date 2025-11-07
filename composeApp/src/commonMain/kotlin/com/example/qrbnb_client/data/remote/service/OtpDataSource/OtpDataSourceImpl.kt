package com.example.qrbnb_client.data.remote.service.OtpDataSource

import com.example.qrbnb_client.data.remote.model.otpResponsesDto.OtpResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class OtpDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : OtpDataSource {
    override suspend fun sendOtp(phoneNumber: String): OtpResponseDto =
        try {
            val otpUrl = "$baseUrl/client/auth/send-otp"
            println("OTP URL: $otpUrl")
            println("Request Body: phoneNumber = $phoneNumber")

            val requestBody = mapOf("phoneNumber" to phoneNumber)

            val response = httpClient.post(otpUrl) {
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }

            response.body<OtpResponseDto>()

        } catch (e: Exception) {
            println("Error sending OTP: ${e.message}")
            throw e
        }
}