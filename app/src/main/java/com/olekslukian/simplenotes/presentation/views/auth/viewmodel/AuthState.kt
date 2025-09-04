package com.olekslukian.simplenotes.presentation.views.auth.viewmodel

import com.olekslukian.simplenotes.core.architecture.ValueObject
import com.olekslukian.simplenotes.core.valueobjects.EmailValueObject
import com.olekslukian.simplenotes.core.valueobjects.NonEmptyStringValueObject
import com.olekslukian.simplenotes.core.valueobjects.PasswordValueObject

enum class AuthStatus {
    INITIAL,
    LOADING,
    SUCCESS,
    FAILURE
}

data class AuthState(
    val email: NonEmptyStringValueObject = NonEmptyStringValueObject.invalid(),
    val password: NonEmptyStringValueObject = NonEmptyStringValueObject.invalid(),
    val passwordError: Boolean = false,
    val emailError : Boolean = false,
    val authStatus: AuthStatus = AuthStatus.INITIAL,
    val error: ValueObject<String> = ValueObject.invalid()
)


