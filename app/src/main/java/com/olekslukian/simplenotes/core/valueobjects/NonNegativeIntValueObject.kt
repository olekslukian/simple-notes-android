package com.olekslukian.simplenotes.core.valueobjects

import com.olekslukian.simplenotes.core.architecture.CustomValueObject

data class NonNegativeIntValueObject(override val value: Int?) : CustomValueObject<Int>(
    value =  value,  validator = { it != null && it >= 0 }
) {
    companion object {
        fun invalid(): NonNegativeIntValueObject = NonNegativeIntValueObject(null)
    }
}

