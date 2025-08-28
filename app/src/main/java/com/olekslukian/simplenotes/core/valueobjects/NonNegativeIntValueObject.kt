package com.olekslukian.simplenotes.core.valueobjects

import com.olekslukian.simplenotes.core.architecture.CustomValueObject

class NonNegativeIntValueObject(value: Int?) : CustomValueObject<Int>(
    value =  value,  validator = { it != null && it >= 0 }
)

