package com.lombaa.firetv.base.lifecycle

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.modifyValue(newValue: T?) {
    if (this.value != newValue) {
        this.value = newValue
    }
}