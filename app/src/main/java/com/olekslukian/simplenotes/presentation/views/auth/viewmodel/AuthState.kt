package com.olekslukian.simplenotes.presentation.views.auth.viewmodel

import com.olekslukian.simplenotes.core.architecture.ValueObject
import com.olekslukian.simplenotes.core.valueobjects.EmailValueObject
import com.olekslukian.simplenotes.core.valueobjects.PasswordValueObject

enum class AuthStatus {
    INITIAL,
    LOADING,
    SUCCESS,
    FAILURE
}

data class AuthState(
    val email: EmailValueObject = EmailValueObject.invalid(),
    val password: PasswordValueObject = PasswordValueObject.invalid(),
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isLoginEnabled: Boolean = false,
    val authStatus: AuthStatus = AuthStatus.INITIAL,
    val error: ValueObject<String> = ValueObject.invalid()
)


