package org.d3if3067.assesment1fix.navigation

import org.d3if3067.assesment1fix.ui.screen.KEY_LAUNDRY

sealed class Screen(val route: String) {
    data object Home: Screen("homeScreen")
    data object Laundry : Screen ("mainScreen")
    data object Menu : Screen ("menuCatatan")
    data object About: Screen("aboutScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_LAUNDRY}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}