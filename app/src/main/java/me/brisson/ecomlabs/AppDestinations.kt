package me.brisson.ecomlabs

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import me.brisson.ecomlabs.AppDestinationsArgs.SEARCH_INPUT_ARGS
import me.brisson.ecomlabs.AppScreens.HOME_SCREEN
import me.brisson.ecomlabs.AppScreens.SEARCH_SCREEN

private object AppScreens {
    const val HOME_SCREEN = "home"
    const val SEARCH_SCREEN = "search"
}

object AppDestinationsArgs {
    const val SEARCH_INPUT_ARGS = "search_input"
}

object AppDestinations {
    const val HOME_ROUTE = HOME_SCREEN
    const val SEARCH_ROUTE = "$SEARCH_SCREEN?$SEARCH_INPUT_ARGS={$SEARCH_INPUT_ARGS}"
}

class AppNavigationActions(private val navController: NavHostController) {

    fun navigateToSearch(search_input: String) {
        val route = SEARCH_SCREEN.let {
            if (search_input.isNotEmpty()) "$it?$SEARCH_INPUT_ARGS=$search_input" else it
        }
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
