package com.olekslukian.simplenotes.presentation.navigation

sealed class Screen(val route: String) {
    object Auth: Screen("auth")
    object Register: Screen("register")
    object Home: Screen("home")
}