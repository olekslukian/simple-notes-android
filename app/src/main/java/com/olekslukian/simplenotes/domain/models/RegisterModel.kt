package com.olekslukian.simplenotes.domain.models

import com.olekslukian.simplenotes.core.architecture.DomainModel
import com.olekslukian.simplenotes.core.architecture.IValidable
import com.olekslukian.simplenotes.core.architecture.ValueObject

data class RegisterModel(
    val email: ValueObject<String>,
    val password: ValueObject<String>,
    val passwordConfirmation: ValueObject<String>
) : DomainModel() {
    override val validationProperties: List<IValidable>
        get() = listOf(email, password, passwordConfirmation)
}
