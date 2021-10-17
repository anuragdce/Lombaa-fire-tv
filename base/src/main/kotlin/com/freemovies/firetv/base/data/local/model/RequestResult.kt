package com.freemovies.firetv.base.data.local.model

sealed class RequestResult<out T> {
    object Cancel : RequestResult<Nothing>()
    object Loading : RequestResult<Nothing>()
    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(val throwable: Throwable) : RequestResult<Nothing>() {
        companion object {
            fun defaultRequestError() = Error(Exception("Unexpected result"))
        }
    }
}

val RequestResult<*>.succeeded
    get() = this is RequestResult.Success && data != null

val <T> RequestResult<T>.data
    get() = if (this.succeeded) (this as RequestResult.Success<T>).data else null

val <T> RequestResult<T>.errorCause
    get() = if(this is RequestResult.Error) throwable else null