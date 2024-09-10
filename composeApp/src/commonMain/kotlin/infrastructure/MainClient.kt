package infrastructure

import com.sdui.dashboard.BuildKonfig
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import models.ServerDrivenScreenResponse


object MainClient {

    suspend fun getRawResponse(url: String): Result<String> = request {
        client.get(BuildKonfig.API_URL + url)
    }

    suspend fun updateResponse(url: String, response: ServerDrivenScreenResponse): Result<Unit> = request {
        client.put(BuildKonfig.API_URL + url) {
            contentType(ContentType.Application.Json)
            setBody(response)
        }
    }

}