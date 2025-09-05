package com.olekslukian.simplenotes.domain.models

import com.olekslukian.simplenotes.core.architecture.DomainModel
import com.olekslukian.simplenotes.core.architecture.IValidable
import com.olekslukian.simplenotes.core.valueObjects.EmailValueObject
import com.olekslukian.simplenotes.core.valueObjects.NonEmptyStringValueObject
import com.olekslukian.simplenotes.core.valueObjects.PasswordValueObject

data class RegisterModel(
    val email: EmailValueObject,
    val password: PasswordValueObject,
    val passwordConfirmation: NonEmptyStringValueObject
) : DomainModel() {
    override val validationProperties: List<IValidable>
        get() = listOf(email, password, passwordConfirmation)
}
