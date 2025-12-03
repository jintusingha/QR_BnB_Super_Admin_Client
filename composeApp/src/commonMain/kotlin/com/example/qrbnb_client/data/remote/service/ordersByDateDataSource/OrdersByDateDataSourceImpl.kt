package com.example.qrbnb_client.data.remote.service.ordersByDateDataSource

import com.example.qrbnb_client.data.remote.model.ordersByDateDto.OrdersByDateResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class OrdersByDateDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : OrdersByDateDataSource {
    override suspend fun getOrdersByDate(date: String): OrdersByDateResponseDto {
        val url = "$baseUrl/orders/by-date?date=$date"

        println("Requesting orders for date: $date")
        println("URL = $url")

        val response = httpClient.get(url)
        println("📡 Status: ${response.status}")

        val rawBody = response.bodyAsText()
        println(" Raw Response: $rawBody")

        return try {
            val dto = response.body<OrdersByDateResponseDto>()
            println(" Parsed DTO: $dto")
            dto
        } catch (e: Exception) {
            println("Error parsing DTO: ${e.message}")
            throw e
        }
    }
}
