package domain.repositories


object ResponseHandler {
    suspend operator fun <T : Any> invoke(apiCall: suspend () -> T): ActionResult<T> {
        return try {
            ActionResult.Success(apiCall())
        } catch (ex: Throwable) {
            ActionResult.Error(ex.message?: ex.toString())
        }
    }
}

sealed class ActionResult<out T : Any> {
    data class Error(val error: String) : ActionResult<Nothing>()
    data class Success<out T : Any>(val value: T) : ActionResult<T>()
}