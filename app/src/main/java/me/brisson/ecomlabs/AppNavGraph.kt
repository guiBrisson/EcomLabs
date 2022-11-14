package me.brisson.ecomlabs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.brisson.ecomlabs.AppDestinationsArgs.SEARCH_INPUT_ARGS
import me.brisson.ecomlabs.presentation.home.HomeScreen
import me.brisson.ecomlabs.presentation.search.SearchScreen

@ExperimentalMaterial3Api
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.HOME_ROUTE,
    navAction: AppNavigationActions = remember(navController) {
        AppNavigationActions(navController)
    }
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = AppDestinations.HOME_ROUTE) {
            HomeScreen(onSearch = { input ->
                navAction.navigateToSearch(input)
            })
        }

        composable(
            route = AppDestinations.SEARCH_ROUTE,
            arguments = listOf(
                navArgument(SEARCH_INPUT_ARGS) { type = NavType.StringType; defaultValue = "" }
            )
        ) {
            SearchScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }

}
