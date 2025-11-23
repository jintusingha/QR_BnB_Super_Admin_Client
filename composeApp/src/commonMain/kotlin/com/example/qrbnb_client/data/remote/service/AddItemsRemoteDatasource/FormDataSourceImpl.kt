package com.example.qrbnb_client.data.remote.service.AddItemsRemoteDatasource

import com.example.qrbnb_client.data.remote.model.AddItemsDto.DynamicFormDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.headers

class FormDataSourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : FormDataSource {
    override suspend fun getAddItemForm(): DynamicFormDto {
        try {
            val url = "$baseUrl/client/items/form/add"
            println("Fetching Add Item Form from: $url")

            val response: HttpResponse =
                httpClient.get(url) {
                    headers {
                        set("Accept", ContentType.Application.Json.toString())
                    }
                }

            println("Form response status: ${response.status}")

            val rawText = response.bodyAsText()
            println("RAW FORM RESPONSE:\n$rawText")

            val body = response.body<DynamicFormDto>()
            println("Form fetched successfully: ${body.message}")

            return body
        } catch (e: Exception) {
            println("Error fetching Add Item Form: ${e.message}")
            throw e
        }
    }
}
