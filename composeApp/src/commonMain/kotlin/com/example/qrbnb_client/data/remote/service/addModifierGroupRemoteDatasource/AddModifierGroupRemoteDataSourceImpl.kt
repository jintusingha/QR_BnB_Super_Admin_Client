package com.example.qrbnb_client.data.remote.service.addModifierGroupRemoteDatasource

import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupRequestDto
import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupResponseDto
import com.example.qrbnb_client.data.remote.model.addModifierGroup.ModifierGroupDataDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AddModifierGroupRemoteDataSourceImpl(
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : AddModifierGroupRemoteDataSource {

    override suspend fun addModifierGroup(request: AddModifierGroupRequestDto): AddModifierGroupResponseDto {
        return try {
            val url = "$baseUrl/client/menu/config/modifier-groups"
            println("Creating Modifier Group: $url")
            println("REQUEST BODY: $request")

            val response: HttpResponse =
                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }

            println("Modifier Group response status: ${response.status}")
            val rawText = response.bodyAsText()
            println("RAW MODIFIER GROUP RESPONSE:\n$rawText")

            response.body()
        } catch (e: Exception) {
            println("Error creating modifier group: ${e.message}")
            throw e
        }
    }
}