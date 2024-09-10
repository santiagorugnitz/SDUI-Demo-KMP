package com.sdui.dashboard.model

import com.sdui.dashboard.model.database.ScreenDAO
import com.sdui.dashboard.model.database.ScreenTable
import com.sdui.dashboard.model.database.suspendTransaction
import jsonFormatter
import kotlinx.serialization.encodeToString
import models.ServerDrivenScreenResponse
import org.jetbrains.exposed.sql.upsert

class DatabaseRepository : IRepository {
    override suspend fun getScreen(identifier: String): ServerDrivenScreenResponse {
        return suspendTransaction { jsonFormatter.decodeFromString<ServerDrivenScreenResponse>(ScreenDAO[identifier].json) }
    }

    override suspend fun updateScreen(identifier: String, value: ServerDrivenScreenResponse) {
        suspendTransaction {
            ScreenTable.upsert {
                it[id] = identifier
                it[json] = jsonFormatter.encodeToString(value)
            }

        }
    }
}