package com.example.qrbnb_client.data.remote.service.AddItemsRemoteDatasource

import com.example.qrbnb_client.data.remote.service.addItemFormSubmitDataSource.ItemDataSource
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*

class ItemDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : ItemDataSource {
    override suspend fun submitItem(
        formId: String,
        values: Map<String, Any?>,
    ) {
        val url = "$baseUrl/client/items"

        val payload =
            buildJsonObject {
                put("formId", formId)

                put(
                    "values",
                    buildJsonObject {
                        values.forEach { (key, value) ->
                            put(key, value.toJsonElement())
                        }
                    },
                )
            }





        try {
            val response =
                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(payload)
                }

            println("SUBMIT RESPONSE STATUS: ${response.status}")
            println("SUBMIT RESPONSE BODY: ${response.bodyAsText()}")
        } catch (e: Exception) {
            println("SUBMIT ERROR: ${e.message}")
            throw e
        }
    }
}

fun Any?.toJsonElement(): JsonElement =
    when (this) {
        null -> JsonNull

        is String -> JsonPrimitive(this)

        is Number -> JsonPrimitive(this)

        is Boolean -> JsonPrimitive(this)

        is List<*> ->
            JsonArray(this.map { it.toJsonElement() })

        is Map<*, *> ->
            JsonObject(
                this
                    .map {
                        (it.key.toString()) to it.value.toJsonElement()
                    }.toMap(),
            )

        else ->
            JsonPrimitive(this.toString())
    }
