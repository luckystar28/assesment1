package org.d3if3067.assesment1fix.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3067.assesment1fix.ui.screen.AboutScreen
import org.d3if3067.assesment1fix.ui.screen.DetailScreen
import org.d3if3067.assesment1fix.ui.screen.HomeScreen
import org.d3if3067.assesment1fix.ui.screen.KEY_LAUNDRY
import org.d3if3067.assesment1fix.ui.screen.MainScreen
import org.d3if3067.assesment1fix.ui.screen.MenuCatatan


@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Laundry.route){
            MainScreen(navController)
        }
        composable(route = Screen.Menu.route){
            MenuCatatan(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_LAUNDRY) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_LAUNDRY)
            DetailScreen(navController, id)
        }
    }
}