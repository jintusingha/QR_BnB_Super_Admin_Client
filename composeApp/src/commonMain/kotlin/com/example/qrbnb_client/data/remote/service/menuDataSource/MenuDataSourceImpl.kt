package com.example.qrbnb_client.data.remote.service.menuDataSource

import com.example.qrbnb_client.data.remote.model.MenuReponseDto.MenuResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class MenuDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : MenuDataSource {

    override suspend fun getMenu(): MenuResponseDto {
        val url = "$baseUrl/client/manual-order/menu"

        println("Requesting Menu from: $url")

        val response = httpClient.get(url)
        println("Response Status: ${response.status}")

        val rawBody = response.bodyAsText()
        println("Raw Response Body: $rawBody")

        return try {
            val dto = response.body<MenuResponseDto>()
            println("Parsed DTO: $dto")
            dto
        } catch (e: Exception) {
            println("Error parsing DTO: ${e.message}")
            throw e
        }
    }
}