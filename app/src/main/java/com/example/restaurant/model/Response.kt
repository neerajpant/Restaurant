package com.example.restaurant.model




sealed class Response<T>(
    val status: Status,
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Response<T>(status = Status.SUCCESS, data = data)
    class Loading<T>(data: T? = null) : Response<T>(status = Status.LOADING, data = data)
    class Error<T>(throwable: Throwable, data: T? = null) :
        Response<T>(status = Status.ERROR, data = data, error = throwable)
}