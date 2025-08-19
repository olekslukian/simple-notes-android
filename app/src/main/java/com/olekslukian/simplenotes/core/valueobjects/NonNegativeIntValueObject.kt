package com.olekslukian.simplenotes.core.valueobjects

import com.olekslukian.simplenotes.core.architecture.CustomValueObject
import com.olekslukian.simplenotes.core.architecture.ValueObject

object NonNegativeIntValueObject : CustomValueObject<Int>(
    validator = { it != null && it >= 0 }
) {
    operator fun invoke(value: Int?) : ValueObject<Int> = create(value)
}
