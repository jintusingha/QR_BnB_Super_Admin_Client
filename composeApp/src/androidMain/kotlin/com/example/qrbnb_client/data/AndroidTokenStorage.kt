package com.example.qrbnb_client.data

import android.content.Context


class AndroidTokenStorage(
    context: Context,
) : TokenStorage {
    private val prefs = context.getSharedPreferences("clientprefs", Context.MODE_PRIVATE)
    private val accessTokenKey = "ACCESS_TOKEN"
    private val refreshTokenKey = "REFRESH_TOKEN"
    private val phoneNumberKey = "PHONE_NUMBER"

    override fun saveTokens(
        accessToken: String,
        refreshToken: String,
    ) {
        prefs
            .edit()
            .putString(accessTokenKey, accessToken)
            .putString(refreshTokenKey, refreshToken)
            .apply()
    }


    override fun savePhoneNumber(phoneNumber: String) {
        prefs
            .edit()
            .putString(phoneNumberKey, phoneNumber)
            .apply()
    }


    override fun getPhoneNumber(): String? {

        return prefs.getString(phoneNumberKey, null)
    }

    override fun getAccessToken(): String? = prefs.getString(accessTokenKey, null)

    override fun getRefreshToken(): String? = prefs.getString(refreshTokenKey, null)

    override fun clearTokens() {
        prefs
            .edit()
            .remove(accessTokenKey)
            .remove(refreshTokenKey)
            .remove(phoneNumberKey)
            .apply()
    }
}