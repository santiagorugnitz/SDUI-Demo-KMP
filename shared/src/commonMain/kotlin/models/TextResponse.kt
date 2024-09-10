package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TextResponse(
    @SerialName("text") val text: String,
    @SerialName("alignment") val textAlign: String? = null,
    @SerialName("color") val color: String? = null,
    @SerialName("size") val fontSize: Int? = null,
    @SerialName("weight") val fontWeight: String? = null,
    @SerialName("max_lines") val maxLines: Int? = null,
) : ServerDrivenComponentResponse()