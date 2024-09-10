package domain.repositories

import ui.screens.dashboard.Endpoint

interface IRepository {
    suspend fun getRawResponse(endpoint: Endpoint): ActionResult<String>
    suspend fun updateResponse(endpoint: Endpoint, json: String): ActionResult<Unit>
    fun getFromCache(endpoint: Endpoint): String
}
