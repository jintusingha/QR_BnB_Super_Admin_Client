package com.example.qrbnb_client.data.remote.service.manageCategoryDataSource

import com.example.qrbnb_client.data.remote.model.deleteCategoryResponseDto.DeleteCategoryResponseDto
import com.example.qrbnb_client.data.remote.model.manageCategoriesDto.ManageCategoryResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class ManageCategoryDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : ManageCategoryDataSource {
    override suspend fun getManageCategory(): ManageCategoryResponseDto {
        val url = "$baseUrl/client/categories"
        println(" Fetching category data from: $url")

        return try {
            val response: HttpResponse =
                httpClient.get(url) {
                    contentType(ContentType.Application.Json)
                }

            println(" Category response status: ${response.status}")

            if (!response.status.isSuccess()) {
                throw Exception("Failed to fetch categories. Status: ${response.status}")
            }

            val body = response.body<ManageCategoryResponseDto>()
            println(" Categories received: ${body.message}")

            body
        } catch (e: Exception) {
            println(" Error fetching categories: ${e.message}")
            throw e
        }
    }

    override suspend fun deleteCategory(id: String): DeleteCategoryResponseDto {
        val response=httpClient.delete("$baseUrl/client/categories/$id")
        return response.body()
    }
}
