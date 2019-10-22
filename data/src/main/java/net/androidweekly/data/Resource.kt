package net.androidweekly.data

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
sealed class Resource<out T> {

    data class Success<out T>(val data: T?) : Resource<T>()

    data class Error<out T>(val serverError: Throwable) : Resource<T>()

    data class Loading<out T>(val show: Boolean) : Resource<T>()

    open fun ignoreElement(): Resource<Unit> {
        return when (this) {
            is Success -> Success(Unit)
            is Error -> Error(serverError = serverError)
            is Loading -> Loading(show = show)
        }
    }
}
