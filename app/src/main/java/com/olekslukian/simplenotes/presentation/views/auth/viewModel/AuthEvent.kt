package com.olekslukian.simplenotes.presentation.views.auth.viewModel

import com.olekslukian.simplenotes.core.valueObjects.NonEmptyStringValueObject

sealed class AuthEvent {
    data class EmailChanged(val email: NonEmptyStringValueObject) : AuthEvent()
    data class PasswordChanged(val password: NonEmptyStringValueObject) : AuthEvent()
    object LoginEvent : AuthEvent()
    object ErrorDismissed : AuthEvent()
}

sealed class AuthNavigationEvent {
    object NavigateToHome : AuthNavigationEvent()
}