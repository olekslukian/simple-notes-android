package com.olekslukian.simplenotes.domain.models

import com.olekslukian.simplenotes.core.architecture.DomainModel
import com.olekslukian.simplenotes.core.architecture.IValidable
import com.olekslukian.simplenotes.core.architecture.ValueObject

data class AuthTokensModel(
    val accessToken: ValueObject<String>,
    val refreshToken: ValueObject<String>
) : DomainModel() {
    override val validationProperties: List<IValidable>
    get() = listOf(accessToken, refreshToken)
}
