package com.skills.newsapp.utils
/**
 *  Article repository handling logic for fetching data either from Database or from network,
 *  Uses NetworkBoundResource for handling this logic
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
