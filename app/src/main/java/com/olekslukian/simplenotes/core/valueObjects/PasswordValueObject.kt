package com.olekslukian.simplenotes.core.valueObjects

import com.olekslukian.simplenotes.core.architecture.CustomValueObject

data class PasswordValueObject(override val value: String?) : CustomValueObject<String>(
    value =  value?.trim(),
    validator = {it != null && it.isNotBlank() && it.length >= 6 }
) {
    companion object {
        fun invalid(): PasswordValueObject = PasswordValueObject(null)
    }
}

