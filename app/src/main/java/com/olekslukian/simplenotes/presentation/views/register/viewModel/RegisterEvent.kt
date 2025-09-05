package com.olekslukian.simplenotes.presentation.views.register.viewModel

import com.olekslukian.simplenotes.core.valueObjects.EmailValueObject
import com.olekslukian.simplenotes.core.valueObjects.NonEmptyStringValueObject
import com.olekslukian.simplenotes.core.valueObjects.PasswordValueObject

sealed class RegisterEvent {
    data class EmailChanged(val email: EmailValueObject) : RegisterEvent()
    data class PasswordChanged(val password: PasswordValueObject) : RegisterEvent()
    data class  PasswordConfirmationChanged(val passwordConfirmation: NonEmptyStringValueObject) : RegisterEvent()
    object RegistrationEvent : RegisterEvent()
    object ErrorDismissed : RegisterEvent()
}

sealed class RegisterNavigationEvent {
    object NavigateToLogin : RegisterNavigationEvent()
}