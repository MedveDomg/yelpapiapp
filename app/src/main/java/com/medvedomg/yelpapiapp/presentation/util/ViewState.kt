package com.medvedomg.yelpapiapp.presentation.util

sealed class ViewState<out R> {

    data class Success<out T>(
        val data: T
    ) : ViewState<T>()

    data class Error<out T>(
        val errorData: T? = null,
        val error: Throwable? = null,
        val errorMessage: String? = null
    ) : ViewState<T>()

    object Loading : ViewState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error<*> -> "Error[error=$error]"
            is Loading -> "Loading"
        }
    }

    fun isSuccess() = this is Success<*>

    fun isError() = this is Error<*>

    fun isLoading() = this is Loading
}