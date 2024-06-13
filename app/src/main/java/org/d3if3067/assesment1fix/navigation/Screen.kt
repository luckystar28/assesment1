package org.d3if3067.assesment1fix.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("homeScreen")
    data object Laundry : Screen ("mainScreen")
    data object Menu : Screen ("menuCatatan")
    data object About: Screen("aboutScreen")
}