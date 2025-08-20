package com.olekslukian.simplenotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.olekslukian.simplenotes.presentation.views.auth.AuthView
import com.olekslukian.simplenotes.presentation.views.home.HomeView

@Composable
fun Navigator(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route,
        modifier = modifier
    ) {
       composable(Screen.Auth.route) {
           AuthView(
               onNavigateToHome = {
                   navController.navigate(Screen.Home.route)
               }
           )
       }

        composable(Screen.Home.route) {
            HomeView(
                onNavigateToAuth = {
                    navController.navigate(Screen.Auth.route)
                }
            )
        }
    }
}

