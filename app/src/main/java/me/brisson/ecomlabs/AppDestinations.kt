package me.brisson.ecomlabs

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import me.brisson.ecomlabs.AppDestinations.SEARCH_ROUTE
import me.brisson.ecomlabs.AppDestinationsArgs.SEARCH_INPUT
import me.brisson.ecomlabs.AppScreens.HOME_SCREEN
import me.brisson.ecomlabs.AppScreens.SEARCH_SCREEN

private object AppScreens {
    const val HOME_SCREEN = "home"
    const val SEARCH_SCREEN = "search"
}

object AppDestinationsArgs {
    const val SEARCH_INPUT = "search_input"
}

object AppDestinations {
    const val HOME_ROUTE = HOME_SCREEN
    const val SEARCH_ROUTE = "$SEARCH_SCREEN/{${SEARCH_INPUT}}"
}

class AppNavigationActions(private val navController: NavHostController) {

    fun navigateToSearch(search_input: String) {
        val route =
            if (search_input.isEmpty()) SEARCH_ROUTE
            else "$SEARCH_SCREEN/$search_input"

        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
