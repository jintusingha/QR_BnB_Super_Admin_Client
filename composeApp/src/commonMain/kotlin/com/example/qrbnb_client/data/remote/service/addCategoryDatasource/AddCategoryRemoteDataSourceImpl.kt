package com.example.qrbnb_client.data.remote.service.addCategoryDatasource

import com.example.qrbnb_client.data.remote.model.addCategoryDto.AddCategoryRequestDto
import com.example.qrbnb_client.data.remote.model.addCategoryDto.AddCategoryResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AddCategoryRemoteDataSourceImpl (private val httpClient: HttpClient,
    private val baseUrl:String): AddCategoryRemoteDataSource{
    override suspend fun addCategory(request: AddCategoryRequestDto): AddCategoryResponseDto {
        return httpClient.post("$baseUrl/client/categories") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

}