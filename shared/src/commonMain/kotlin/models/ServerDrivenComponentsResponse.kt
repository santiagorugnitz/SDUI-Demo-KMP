package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerDrivenScreenResponse(
    @SerialName("header") val header: List<ServerDrivenComponentResponse>,
    @SerialName("content") val content: List<ServerDrivenComponentResponse>,
    @SerialName("footer") val footer: List<ServerDrivenComponentResponse>,
)


@Serializable
sealed class ServerDrivenComponentResponse
