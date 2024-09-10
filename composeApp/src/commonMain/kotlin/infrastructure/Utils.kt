package infrastructure

import androidx.compose.ui.graphics.Color
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import jsonFormatter
import kotlinx.serialization.json.Json


suspend inline fun <reified T> request(
    requester: () -> HttpResponse
): Result<T> = try {
    val httpResponse: HttpResponse = requester()
    val response: T = httpResponse.body()
    Result.success(response)
} catch (exception: ResponseException) {
    Result.failure(exception)
} catch (exception: Throwable) {
    exception.printStackTrace()
    Result.failure(exception)
}

fun parseColor(value: String) = Color(("ff" + value.removePrefix("#").lowercase()).toLong(16))


val client = HttpClient {
    install(ContentNegotiation) { json(Json(jsonFormatter) { ignoreUnknownKeys = true }) }
    install(Logging) { logger = Logger.DEFAULT }
}