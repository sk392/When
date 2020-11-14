package `when`.sangdami.com.domain.entity


sealed class Result<out T> {

    companion object {
        inline fun <T> create(block: () -> T) = try {
            Success(block.invoke())
        } catch (e: Exception) {
            Failure<T>(e)
        }
    }

    data class Success<T>(val value: T) : Result<T>()

    data class Failure<T>(val throwable: Exception) : Result<T>()
}
