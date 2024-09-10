package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpacerResponse(
    @SerialName("height") val height: Int,
): ServerDrivenComponentResponse()