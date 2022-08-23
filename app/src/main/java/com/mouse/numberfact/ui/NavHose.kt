package com.mouse.numberfact.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mouse.numberfact.data.NumberFact
import com.mouse.numberfact.data.NumberFactType

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
        composable(
            "detail/{$EXTRA_NUMBER}",
            arguments = listOf(navArgument(EXTRA_NUMBER) {
                type = NumberFactType()
            })
        ) { backStackEntry ->
            val number = backStackEntry.arguments?.getParcelable(EXTRA_NUMBER) ?: NumberFact()
            DetailScreen(numberFact = number)
        }
    }
}

private const val EXTRA_NUMBER = "extra_number"
