package com.example.qrbnb_client.data.remote.service.addVariantRemoteDatasource

import com.example.qrbnb_client.data.remote.model.addVariant.AddVariantRequestDto
import com.example.qrbnb_client.data.remote.model.addVariant.AddVariantResponseDto
import com.example.qrbnb_client.domain.entity.addVariant.VariantEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AddVariantRemoteDataSourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : AddVariantRemoteDataSource {
    override suspend fun addVariant(entity: AddVariantRequestDto): AddVariantResponseDto {
        val url = "$baseUrl/client/menu/config/variants"

        val response: HttpResponse =
            httpClient.post(url) {
                contentType(ContentType.Application.Json)
                setBody(entity)
            }

        println("RESPONSE: ${response.bodyAsText()}")

        return response.body()
    }
}
