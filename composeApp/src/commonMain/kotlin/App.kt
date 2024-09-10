
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screens.dashboard.DashboardScreen


@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController()
) {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        NavHost(
            navController = navController,
            startDestination = "/",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("/"){
                DashboardScreen()
            }
        }
    }
}