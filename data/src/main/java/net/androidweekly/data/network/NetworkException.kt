package net.androidweekly.data.network

/**
 * Project: MoveFast
 * Created: Oct 28, 2019
 *
 * @author Mohamed Hamdan
 */
sealed class NetworkException(
    val resourceTitle: Int,
    val resourceMessage: Int,
    message: String?,
    cause: Throwable
) : RuntimeException(message, cause) {

    class Client(
        resourceTitle: Int,
        resourceMessage: Int,
        message: String?,
        cause: Throwable
    ) : NetworkException(resourceTitle, resourceMessage, message, cause)

    class Server(
        resourceTitle: Int,
        resourceMessage: Int,
        message: String?,
        cause: Throwable
    ) : NetworkException(resourceTitle, resourceMessage, message, cause)

    class Timeout(
        resourceTitle: Int,
        resourceMessage: Int,
        message: String?,
        cause: Throwable
    ) : NetworkException(resourceTitle, resourceMessage, message, cause)

    class Internet(
        resourceTitle: Int,
        resourceMessage: Int,
        message: String?,
        cause: Throwable
    ) : NetworkException(resourceTitle, resourceMessage, message, cause)

    class Unexpected(
        resourceTitle: Int,
        resourceMessage: Int,
        message: String?,
        cause: Throwable
    ) : NetworkException(resourceTitle, resourceMessage, message, cause)
}
