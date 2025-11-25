package com.example.qrbnb_client.data

interface UriHelper {
    fun getFileNameFromUri(uriString: String): String

    fun getBytesFromUri(uriString: String): ByteArray

    fun getMimeType(uriString: String): String
}
