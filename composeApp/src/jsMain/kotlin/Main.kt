import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        val title = "SDUI"
        @OptIn(ExperimentalComposeUiApi::class)
        CanvasBasedWindow(title, canvasElementId = "ComposeTarget") {
           App()
        }
    }
}
