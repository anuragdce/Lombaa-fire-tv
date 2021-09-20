package com.lombaa.firetv.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

private const val LOG_TAG = "iScapeTAG"

val <T> T.exhaustive: T
    get() = this

fun log(message: String) {
    Log.i(LOG_TAG, message)
}

fun log(throwable: Throwable) {
    Log.w(LOG_TAG, "${throwable.javaClass.name}: ${Log.getStackTraceString(throwable)}")
}

fun <T> anyNotNull(vararg elements: T) = elements.any { it != null }

fun <K, V> Map<K, V>.asMutable() = this as MutableMap

fun <T> List<T>.asMutable() = this as MutableList

fun <T> Set<T>.asMutable() = this as MutableSet

fun <T> LiveData<T>.asMutable() = this as MutableLiveData