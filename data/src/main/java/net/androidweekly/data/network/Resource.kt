package net.androidweekly.data.network

import net.androidweekly.data.R
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Project: MoveFast
 * Created: Oct 28, 2019
 *
 * @author Mohamed Hamdan
 */
sealed class Resource {

    data class Success<out T>(val data: T?) : Resource()

    data class Error(val error: NetworkException) : Resource()

    data class Loading(val show: Boolean) : Resource()

    open fun ignoreElement(): Resource {
        return when (this) {
            is Success<*> -> Success(Unit)
            is Error -> Error(error = error)
            is Loading -> Loading(show = show)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> element(): T? {
        return if (this is Success<*>) {
            data as? T?
        } else {
            null
        }
    }
}

private const val HTTP_CODE_CLIENT_START = 400
private const val HTTP_CODE_CLIENT_END = 499

private const val HTTP_CODE_SERVER_START = 500
private const val HTTP_CODE_SERVER_END = 599

inline fun <T> tryResource(func: () -> T): Resource {
    return try {
        Resource.Success(data = func())
    } catch (e: Exception) {
        Resource.Error(error = getRequestException(e))
    }
}


fun getRequestException(cause: Throwable): NetworkException {
    return when (cause) {
        is HttpException -> {
            handleHttpException(cause)
        }
        is SocketTimeoutException -> {
            NetworkException.Timeout(
                R.string.err_network_timeout_title,
                R.string.err_network_timeout_message,
                cause.message,
                cause
            )
        }
        is IOException -> {
            NetworkException.Internet(
                R.string.err_network_internet_title,
                R.string.err_network_internet_message,
                cause.message,
                cause
            )
        }
        else -> {
            NetworkException.Unexpected(
                R.string.err_network_unexpected_title,
                R.string.err_network_unexpected_message,
                cause.message,
                cause
            )
        }
    }
}

private fun handleHttpException(cause: HttpException): NetworkException {
    return when (cause.code()) {
        in HTTP_CODE_CLIENT_START..HTTP_CODE_CLIENT_END -> {
            NetworkException.Client(
                R.string.err_network_client_title,
                R.string.err_network_client_message,
                cause.message,
                cause
            )
        }
        in HTTP_CODE_SERVER_START..HTTP_CODE_SERVER_END -> {
            NetworkException.Server(
                R.string.err_network_server_title,
                R.string.err_network_server_message,
                cause.message,
                cause
            )
        }
        else -> {
            NetworkException.Unexpected(
                R.string.err_network_unexpected_title,
                R.string.err_network_unexpected_message,
                cause.message,
                cause
            )
        }
    }
}
