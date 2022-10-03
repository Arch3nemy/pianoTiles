package com.alacrity.template

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alacrity.template.Destinations.HOME_ROUTE
import com.alacrity.template.ui.main.MainViewModel

@Composable
fun AppNavGraph(
    context: Context,
    homeViewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(HOME_ROUTE) {
            MainScreen(
                context = context,
                viewModel = homeViewModel,
            )
        }
    }
}
