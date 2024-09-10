package com.sdui.dashboard.model

import models.ServerDrivenScreenResponse

interface IRepository {
    suspend fun getScreen(identifier: String): ServerDrivenScreenResponse
    suspend fun updateScreen(identifier: String, value: ServerDrivenScreenResponse)
}