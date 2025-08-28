package com.olekslukian.simplenotes.core.valueobjects

import com.olekslukian.simplenotes.core.architecture.CustomValueObject
import com.olekslukian.simplenotes.utils.RegExp

class EmailValueObject(value: String?) : CustomValueObject<String>(
    value =  value?.trim(),
    validator = {it != null && it.isNotBlank() && RegExp.emailPattern.matcher(it).matches() }
) {
    companion object {
        fun invalid(): EmailValueObject = EmailValueObject(null)
    }
}
