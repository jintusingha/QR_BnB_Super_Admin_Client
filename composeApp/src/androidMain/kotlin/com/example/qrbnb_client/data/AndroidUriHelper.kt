package com.example.qrbnb_client.data

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.ByteArrayOutputStream

class AndroidUriHelper(private val context: Context) : UriHelper {

    override fun getFileNameFromUri(uriString: String): String {
        val uri = Uri.parse(uriString)
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex != -1 && it.moveToFirst()) {
                it.getString(nameIndex)
            } else {
                "image_${System.currentTimeMillis()}.jpg"
            }
        } ?: "image_${System.currentTimeMillis()}.jpg"
    }

    override fun getBytesFromUri(uriString: String): ByteArray {
        val uri = Uri.parse(uriString)
        val inputStream = context.contentResolver.openInputStream(uri)
            ?: throw IllegalArgumentException("Cannot open URI: $uriString")

        val buffer = ByteArrayOutputStream()
        inputStream.use { input ->
            val data = ByteArray(8192)
            var count: Int
            while (input.read(data).also { count = it } != -1) {
                buffer.write(data, 0, count)
            }
        }

        return buffer.toByteArray()
    }


    override fun getMimeType(uriString: String): String {
        val uri = Uri.parse(uriString)
        return context.contentResolver.getType(uri) ?: "application/octet-stream"
    }
}