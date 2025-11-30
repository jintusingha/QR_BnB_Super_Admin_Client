package com.example.qrbnb_client.data.remote.service.orderDetailsDatasource

import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrdersDetailsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

class OrderDetailsRemoteDatasourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient
) : OrderDetailsRemoteDatasource {

    override suspend fun getOrderDetails(orderId: String): OrdersDetailsResponseDto {

        val url = "$baseUrl/orders/$orderId"
        println("Requesting Order Details from: $url")

        val response: HttpResponse = httpClient.get(url)
        println("Response Status: ${response.status}")

        val raw = response.bodyAsText()
        println("Raw Order Response: $raw")

        return try {
            val dto = response.body<OrdersDetailsResponseDto>()
            println("Parsed DTO: $dto")
            dto
        } catch (e: Exception) {
            println("Error parsing DTO: ${e.message}")
            throw e
        }
    }
}
