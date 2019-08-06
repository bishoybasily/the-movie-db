package com.neugelb.themoviedb.model.entity

class Response<T> {

    var loading: Boolean = false
    var ok: Boolean = false
    var data: T? = null
    var throwable: Throwable? = null

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    fun getStatus(): Status {
        if (loading)
            return Status.LOADING
        else if (!ok && throwable != null)
            return Status.ERROR
        else if (ok && data != null)
            return Status.SUCCESS
        throw IllegalArgumentException()
    }

    companion object {

        fun <T> loading(): Response<T> {
            return Response<T>().apply {
                loading = true
            }
        }

        fun <T> success(t: T): Response<T> {
            return Response<T>().apply {
                ok = true
                loading = false
                data = t
            }
        }

        fun <T> error(err: Throwable): Response<T> {
            return Response<T>().apply {
                ok = false
                loading = false
                throwable = err
            }
        }

    }

}
