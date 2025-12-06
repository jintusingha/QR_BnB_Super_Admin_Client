package com.example.qrbnb_client.data

interface IFilePicker {
    fun launchFilePicker()

    fun setPickedFile(fileData: FileData?)

    fun getPickedFile(): FileData?

    var onLaunchPickerRequest: (() -> Unit)?
}

data class FileData(
    val name: String,
    val size: Long,
    val mimeType: String,
)
