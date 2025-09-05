package com.olekslukian.simplenotes.presentation.views.register.viewModel

import com.olekslukian.simplenotes.core.architecture.ValueObject
import com.olekslukian.simplenotes.core.valueObjects.EmailValueObject
import com.olekslukian.simplenotes.core.valueObjects.NonEmptyStringValueObject
import com.olekslukian.simplenotes.core.valueObjects.PasswordValueObject

enum class RegisterStatus {
    INITIAL,
    LOADING,
    SUCCESS,
    FAILURE
}

data class RegisterState(
    val email: EmailValueObject = EmailValueObject.invalid(),
    val password: PasswordValueObject = PasswordValueObject.invalid(),
    val passwordConfirmation: NonEmptyStringValueObject = NonEmptyStringValueObject.invalid(),
    val passwordError: Boolean = false,
    val emailError : Boolean = false,
    val passwordConfirmationError : Boolean = false,
    val registerStatus: RegisterStatus = RegisterStatus.INITIAL,
    val error: ValueObject<String> = ValueObject.invalid()
)