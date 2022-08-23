package com.mouse.numberfact.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NumberFactNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "main",
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("main") {
            MainScreen(onNavigateToDetail = { number ->
                navController.navigate("detail/$number") {
                    launchSingleTop = true
                }
            })
        }
        composable("detail/{number}") { backStackEntry ->
            val number = backStackEntry.arguments?.getString("number").orEmpty()
            DetailScreen(number = number)
        }
    }
}
