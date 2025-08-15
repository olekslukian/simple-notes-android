package com.olekslukian.simplenotes.core.architecture

interface IValidable {
    val isValid: Boolean
    val isInvalid: Boolean
        get() = !isValid
}