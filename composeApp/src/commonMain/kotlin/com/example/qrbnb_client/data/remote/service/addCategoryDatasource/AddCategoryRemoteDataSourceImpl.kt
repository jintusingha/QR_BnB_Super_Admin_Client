package com.example.qrbnb_client.data.remote.service.addCategoryDatasource

import com.example.qrbnb_client.data.remote.model.addCategoryDto.AddCategoryRequestDto
import com.example.qrbnb_client.data.remote.model.addCategoryDto.AddCategoryResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AddCategoryRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : AddCategoryRemoteDataSource {
    override suspend fun addCategory(request: AddCategoryRequestDto): AddCategoryResponseDto {
        println("Sending POST request to: $baseUrl/client/categories")
        println("Request Body: $request")

        val response =
            httpClient.post("$baseUrl/client/categories") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

        println("Response Status: ${response.status}")

        val raw = response.bodyAsText()
        println("Raw Response Body: $raw")

        return try {
            val dto = response.body<AddCategoryResponseDto>()

            dto
        } catch (e: Exception) {
            println("Parsing Error: ${e.message}")
            throw e
        }
    }
}
