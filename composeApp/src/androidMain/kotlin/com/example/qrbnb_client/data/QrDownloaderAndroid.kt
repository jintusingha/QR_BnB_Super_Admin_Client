package com.example.qrbnb_client.data

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

class QrDownloaderAndroid(
    private val context: Context
) : QrDownloader {

    override fun downloadImage(url: String) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("QR Code Download")
            .setDescription("Downloading QR code...")
            .setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            )
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "qr_${System.currentTimeMillis()}.png"
            )

        val dm = context.getSystemService(DownloadManager::class.java)
        dm.enqueue(request)
    }
}