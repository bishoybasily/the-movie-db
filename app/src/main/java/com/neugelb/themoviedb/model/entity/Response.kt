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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Response<*>

        if (loading != other.loading) return false
        if (ok != other.ok) return false
        if (data != other.data) return false
        if (throwable != other.throwable) return false

        return true
    }

    override fun hashCode(): Int {
        var result = loading.hashCode()
        result = 31 * result + ok.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        result = 31 * result + (throwable?.hashCode() ?: 0)
        return result
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
