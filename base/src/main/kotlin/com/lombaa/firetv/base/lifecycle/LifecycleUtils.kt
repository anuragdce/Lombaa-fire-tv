package com.lombaa.firetv.base.lifecycle

import android.app.Application
import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import com.lombaa.firetv.base.log

val AndroidViewModel.applicationContext: Context
    get() = this.getApplication<Application>().applicationContext

fun AndroidViewModel.getString(@StringRes textId: Int): String {
    return applicationContext.getString(textId)
}

fun AndroidViewModel.getStringArray(@ArrayRes arrayId: Int): Array<String> = applicationContext.resources.getStringArray(arrayId)

fun AndroidViewModel.getIntArray(@ArrayRes arrayId: Int): IntArray {
    return applicationContext.resources.getIntArray(arrayId)
}

fun AndroidViewModel.getImageArray(@ArrayRes arrayId: Int): List<Int> {
    val items = mutableListOf<Int>()
    try {
        val images = applicationContext.resources.obtainTypedArray(arrayId)
        for (index in 0..images.length()) {
            items.add(images.getResourceId(index, 0))
        }
        images.recycle()
    } catch (exception: Exception) {
        log(exception)
    }
    return items
}

val AndroidViewModel.versionInfo: String
    get() = try {
        val info = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0)
        "${info.versionName}.${info.versionCode}"
    } catch (ex: Throwable) {
        ""
    }

