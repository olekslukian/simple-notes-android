package com.olekslukian.simplenotes.domain.models

import com.olekslukian.simplenotes.core.architecture.DomainModel
import com.olekslukian.simplenotes.core.architecture.IValidable
import com.olekslukian.simplenotes.core.architecture.ValueObject

data class ChangePasswordModel(
    val oldPassword: ValueObject<String>,
    val newPassword: ValueObject<String>,
    val confirmNewPassword: ValueObject<String>
) : DomainModel() {
    override val validationProperties: List<IValidable>
        get() = listOf(oldPassword, newPassword, confirmNewPassword)
}
