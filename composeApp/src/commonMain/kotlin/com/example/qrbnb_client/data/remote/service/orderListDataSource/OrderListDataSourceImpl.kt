package com.example.qrbnb_client.data.remote.service.orderListDataSource

import com.example.qrbnb_client.data.remote.model.orderListDto.OrderListResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

class OrderListDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : OrderListDataSource {


    override suspend fun getOrderListData(
        clientId: String,
        status: String?,
    ): OrderListResponseDto =
        try {
            val ordersUrl = "$baseUrl/orders"

            println( "Making GET request to: $ordersUrl with clientId=$clientId, status=$status")
            val response =
                httpClient.get(ordersUrl) {
                    parameter("clientId", clientId)
                    status?.let { parameter("status", it) }
                }
            val bodyText = response.bodyAsText()
            println( "Response status: ${response.status}")
            println("Response body snippet: ${bodyText}")
            val apiResponse = response.body<OrderListResponseDto>()
            println("Parsed response - success: ${apiResponse.success}")
            println( "THE ID IS ${apiResponse.data.orders.first().clientId}")
            apiResponse
        } catch (e: Exception) {
            println("getOrderListData API call failed: ${e.message}")
            throw e
        }
}
