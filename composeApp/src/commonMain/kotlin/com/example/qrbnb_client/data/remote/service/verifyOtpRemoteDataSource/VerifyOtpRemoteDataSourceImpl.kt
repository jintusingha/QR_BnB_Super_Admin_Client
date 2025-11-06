package com.example.qrbnb_client.data.remote.service.verifyOtpRemoteDataSource

import com.example.qrbnb_client.data.remote.model.veriftyOtpDto.VerifyOtpResponseDto
import com.example.qrbnb_client.data.remote.model.verifyotpRequestDto.VerifyOtpRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

class VerifyOtpRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : VerifyOtpRemoteDataSource {
    override suspend fun verifyOtp(request: VerifyOtpRequestDto): VerifyOtpResponseDto {
        val url = "$baseUrl/client/auth/verify-otp"
        println("the url is $url")
        val response =
            httpClient.post(url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        val responseText = response.bodyAsText()
        println("Response body: $responseText")
        return response.body<VerifyOtpResponseDto>()
    }
}
