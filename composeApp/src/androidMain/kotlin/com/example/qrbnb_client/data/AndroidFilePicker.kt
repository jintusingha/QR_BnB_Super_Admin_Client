package com.example.qrbnb_client.data

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

class AndroidFilePicker(
    private val context: Context,
) : IFilePicker {
    override var onLaunchPickerRequest: (() -> Unit)? = null

    private var pickedFile: FileData? = null

    override fun launchFilePicker() {
        onLaunchPickerRequest?.invoke()
    }

    override fun setPickedFile(fileData: FileData?) {
        pickedFile = fileData
    }

    override fun getPickedFile(): FileData? = pickedFile

    fun readFileFromUri(uri: Uri): FileData? {
        try {
            val cr = context.contentResolver

            var fileName = "unknown"
            var fileSize = 0L

            cr.query(uri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val nameIdx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (nameIdx >= 0) fileName = cursor.getString(nameIdx)

                    val sizeIdx = cursor.getColumnIndex(OpenableColumns.SIZE)
                    if (sizeIdx >= 0) fileSize = cursor.getLong(sizeIdx)
                }
            }

            val mimeType = cr.getType(uri) ?: "unknown"

            return FileData(
                name = fileName,
                size = fileSize,
                mimeType = mimeType,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
