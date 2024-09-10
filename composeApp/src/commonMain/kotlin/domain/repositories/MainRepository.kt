package domain.repositories

import infrastructure.MainClient
import jsonFormatter
import models.ServerDrivenScreenResponse
import ui.screens.dashboard.Endpoint

object MainRepository: IRepository {

    private val cache: MutableMap<Endpoint, String> = mutableMapOf()

    override suspend fun getRawResponse(endpoint: Endpoint): ActionResult<String> {
        return ResponseHandler {
            val result = MainClient.getRawResponse(endpoint.path).getOrThrow()
            cache[endpoint] = result
            result
        }
    }

    override suspend fun updateResponse(endpoint: Endpoint, json: String): ActionResult<Unit> {
        return ResponseHandler {
            MainClient.updateResponse(
                endpoint.path,
                jsonFormatter.decodeFromString<ServerDrivenScreenResponse>(json)
            ).getOrThrow()
        }
    }

    override fun getFromCache(endpoint: Endpoint): String {
        return cache[endpoint].orEmpty()
    }

}


