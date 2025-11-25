package com.example.qrbnb_client.data.remote.service.imageUploadRemoteDataSource

import com.example.qrbnb_client.data.TokenStorage
import com.example.qrbnb_client.data.remote.model.ImageUploadResponseDto.UploadResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*



class UploadRemoteDataSourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient,
    private val tokenStorage: TokenStorage
) : UploadRemoteDataSource {

    override suspend fun uploadImage(
        fileName: String,
        fileBytes: ByteArray,
        mimeType: String
    ): String {

        val token = tokenStorage.getAccessToken()
            ?: throw IllegalArgumentException("No access token found!")

        println("Token: ${token.take(20)}...")
        println("File: $fileName (${fileBytes.size} bytes, $mimeType)")

        try {
            val response: UploadResponse = httpClient.submitFormWithBinaryData(
                url = "$baseUrl/client/upload",
                formData = formData {
                    append(
                        key = "file",
                        value = fileBytes,
                        headers = Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"$fileName\"")
                            append(HttpHeaders.ContentType, mimeType)
                        }
                    )
                }
            ) {
                header(HttpHeaders.Authorization, "Bearer $token")
            }.body()

            println("Success! URL: ${response.data.url}")
            return response.data.url

        } catch (e: Exception) {
            println(" Error: ${e.message}")
            throw e
        }
    }
}