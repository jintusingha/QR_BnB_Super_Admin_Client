package com.example.qrbnb_client.data.remote.service.clientDashboardDataSource

import com.example.qrbnb_client.data.remote.model.clientDashboardDto.ClientDashboardResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.headers

class ClientDashboardDatasourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient
) : ClientDashboardDataSource {
    override suspend fun getClientDashboardData(): ClientDashboardResponseDto {
        try {
            val dashboardUrl = "$baseUrl/client/dashboard"
            println("Fetching dashboard data from :$dashboardUrl")
            val response: HttpResponse =
                httpClient.get(dashboardUrl) {
                    headers {
                        // FIX: Use the correct set function to define the header key and value
                        set("Accept", ContentType.Application.Json.toString())
                    }
                }
            println("dashboard response status:${response.status}")
            val body = response.body<ClientDashboardResponseDto>()
            println("Dashboard data received:${body.message}")
            return body
        } catch (e: Exception) {
            println("error fetching dashboard:${e.message}")
            throw e
        }
    }
}
