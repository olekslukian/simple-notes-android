package com.olekslukian.simplenotes.domain.models

import com.olekslukian.simplenotes.core.architecture.DomainModel
import com.olekslukian.simplenotes.core.architecture.IValidable
import com.olekslukian.simplenotes.core.architecture.ValueObject
import com.olekslukian.simplenotes.core.valueobjects.EmailValueObject
import com.olekslukian.simplenotes.core.valueobjects.NonEmptyStringValueObject
import com.olekslukian.simplenotes.core.valueobjects.PasswordValueObject

data class LoginModel(
    val email: NonEmptyStringValueObject,
    val password: NonEmptyStringValueObject
) : DomainModel() {
    override val validationProperties: List<IValidable>
        get() = listOf<IValidable>(email, password)
}
