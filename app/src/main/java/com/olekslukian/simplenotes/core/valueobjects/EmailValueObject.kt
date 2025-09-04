package com.olekslukian.simplenotes.core.valueobjects

import com.olekslukian.simplenotes.core.architecture.CustomValueObject
import com.olekslukian.simplenotes.utils.RegExp
import java.util.regex.Pattern

data class EmailValueObject(override val value: String?) : CustomValueObject<String>(
    value =  value?.trim(),
    validator = {
        it != null && it.isNotBlank() && Pattern.compile(RegExp.EMAIL_PATTERN).matcher(it).matches()
    }
) {
    companion object {
        fun invalid(): EmailValueObject = EmailValueObject(null)
    }
}
