package com.freemovies.firetv.base.extension

import android.graphics.Bitmap
import android.net.Uri
import android.webkit.URLUtil
import androidx.core.net.toFile
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

fun <T> ObjectMapper.readList(content: String, klass: Class<Array<T>>): List<T> {
    val items: Array<T> = readValue(content, klass)
    return listOf(*items)
}

fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
    }
}

fun File.moveTo(target: File) {
    this.copyTo(target, true)
    this.delete()
}

fun Uri.isLocal(): Boolean {
    return !URLUtil.isNetworkUrl(this.toString())
}

fun Uri.delete() {
    if (isLocal()) {
        this.toFile().delete()
    }
}
