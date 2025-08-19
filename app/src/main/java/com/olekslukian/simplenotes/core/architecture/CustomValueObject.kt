package com.olekslukian.simplenotes.core.architecture

abstract class CustomValueObject<T>(private val validator: (T?) -> Boolean) {
     fun create(value: T?) : ValueObject<T> {
        return if (value != null && validator(value)) {
            ValueObject.Valid(value)
        } else {
            ValueObject.Invalid
        }
    }
}