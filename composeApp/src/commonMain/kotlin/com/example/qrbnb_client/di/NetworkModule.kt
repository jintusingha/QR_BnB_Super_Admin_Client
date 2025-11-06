
package com.example.qrbnb_client.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule =
    module {

        single {
            HttpClient {
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                        },
                    )
                }
                install(Logging) {
                    level = LogLevel.BODY
                }
                expectSuccess = true
            }
        }

        single(named("BASE_URL")) { "https://qrbnb.onrender.com" }
    }
