package com.example.qrbnb_client.data

interface TokenStorage {
    fun saveTokens(accessToken:String,refreshToken:String)
    fun savePhoneNumber(phoneNumber: String)
    fun getPhoneNumber(): String?
    fun getAccessToken():String?
    fun getRefreshToken():String?
    fun clearTokens()
}