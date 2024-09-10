package domain.models.server_driven

data class ServerDrivenScreen(
    val header: List<ServerDrivenData>,
    val content: List<ServerDrivenData>,
    val footer: List<ServerDrivenData>,
)
