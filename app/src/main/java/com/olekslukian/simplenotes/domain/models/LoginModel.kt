package com.olekslukian.simplenotes.domain.models

import com.olekslukian.simplenotes.core.architecture.DomainModel
import com.olekslukian.simplenotes.core.architecture.IValidable
import com.olekslukian.simplenotes.core.valueObjects.NonEmptyStringValueObject

data class LoginModel(
    val email: NonEmptyStringValueObject,
    val password: NonEmptyStringValueObject
) : DomainModel() {
    override val validationProperties: List<IValidable>
        get() = listOf<IValidable>(email, password)
}
