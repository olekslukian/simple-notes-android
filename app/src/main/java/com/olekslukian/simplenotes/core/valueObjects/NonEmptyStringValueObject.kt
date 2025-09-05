package com.olekslukian.simplenotes.core.valueObjects

import com.olekslukian.simplenotes.core.architecture.CustomValueObject

data class NonEmptyStringValueObject(override val value: String?) : CustomValueObject<String>(
    value =  value?.trim(),
    validator = {it != null && it.isNotBlank() }
) {
    companion object {
        fun invalid(): NonEmptyStringValueObject = NonEmptyStringValueObject(null)
    }
}