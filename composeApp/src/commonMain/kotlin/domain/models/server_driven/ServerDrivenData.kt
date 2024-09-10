package domain.models.server_driven

sealed class ServerDrivenData {

    interface EventHandler {
        fun onOpenUrl(url: String) {}
        fun onDismiss() {}
    }

}
