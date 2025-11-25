package com.example.qrbnb_client.di

import com.example.qrbnb_client.data.TokenStorage
import com.example.qrbnb_client.data.remote.model.RefreshRequest
import com.example.qrbnb_client.data.remote.model.RefreshResponse
// <-- New Import
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

val PRE_LOGIN_CLIENT = named("preLoginClient")
val POST_LOGIN_CLIENT = named("postLoginClient")

val networkModule =
    module {

        single(named("BASE_URL")) { "https://qrbnb.onrender.com" }

        single(POST_LOGIN_CLIENT) {

            val tokenStorage: TokenStorage = get()
            val baseUrl: String = get(named("BASE_URL"))

            HttpClient {
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                        },
                    )
                }
                install(Logging) { level = LogLevel.BODY }
//                install(Logging) {
//                    logger = object : io.ktor.client.plugins.logging.Logger {
//                        override fun log(message: String) {
//                            println("KTOR --> $message")
//                        }
//                    }
//                    level = LogLevel.ALL
//                }
                expectSuccess = true

                install(Auth) {
                    bearer {

                        loadTokens {
                            tokenStorage.getAccessToken()?.let { accessToken ->

                                BearerTokens(accessToken, tokenStorage.getRefreshToken() ?: "")
                            }
                        }

                        refreshTokens {
                            val refreshToken = tokenStorage.getRefreshToken()
                            val phoneNumber = tokenStorage.getPhoneNumber()

                            if (refreshToken != null && phoneNumber != null) {

                                println("AuthDebug: Attempting to refresh token for $phoneNumber")

                                val response: HttpResponse =
                                    client.post("$baseUrl/client/auth/refresh") {
                                        markAsRefreshTokenRequest()
                                        contentType(ContentType.Application.Json)
                                        setBody(RefreshRequest(phoneNumber, refreshToken))
                                    }

                                if (response.status == HttpStatusCode.OK || response.status == HttpStatusCode.Created) {
                                    val body = response.body<RefreshResponse>()

                                    if (body.success) {
                                        val newAccessToken = body.data.accessToken

                                        val newRefreshToken = refreshToken

                                        println("AuthDebug: Token refreshed successfully!")
                                        tokenStorage.saveTokens(newAccessToken, newRefreshToken)

                                        BearerTokens(newAccessToken, newRefreshToken)
                                    } else {

                                        tokenStorage.clearTokens()
                                        null
                                    }
                                } else {

                                    tokenStorage.clearTokens()
                                    null
                                }
                            } else {

                                null
                            }
                        }
                    }
                }
            }
        }

        single(PRE_LOGIN_CLIENT) {
            HttpClient {
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                        },
                    )
                }
                install(Logging) { level = LogLevel.BODY }
                expectSuccess = true
            }
        }
    }
