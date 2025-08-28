package com.olekslukian.simplenotes.core.valueobjects

import com.olekslukian.simplenotes.core.architecture.CustomValueObject

class PasswordValueObject(value: String?) : CustomValueObject<String>(
    value =  value?.trim(),
    validator = {it != null && it.isNotBlank() && it.length >= 6 }
) {
    companion object {
        fun invalid(): PasswordValueObject = PasswordValueObject(null)
    }
}

