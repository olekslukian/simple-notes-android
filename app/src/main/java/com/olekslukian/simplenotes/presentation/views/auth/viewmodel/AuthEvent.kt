package com.olekslukian.simplenotes.presentation.views.auth.viewmodel

sealed class AuthEvent {
    data class EmailChanged(val email: String) : AuthEvent()
    data class PasswordChanged(val password: String) : AuthEvent()
    object LoginEvent : AuthEvent()
    object ErrorDismissed : AuthEvent()
}

sealed class AuthNavigationEvent {
    object NavigateToHome : AuthNavigationEvent()
}