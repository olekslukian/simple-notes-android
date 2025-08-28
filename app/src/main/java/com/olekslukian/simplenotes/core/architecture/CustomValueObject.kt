package com.olekslukian.simplenotes.core.architecture

abstract class CustomValueObject<T>(
    value: T?,
    private val validator: (T?) -> Boolean
) : IValueObject<T> by createValueObject(value, validator) {

    companion object {
        private fun <T> createValueObject(
            value: T?,
            validator: (T?) -> Boolean
        ): ValueObject<T> {
            return if (value != null && validator(value)) {
                ValueObject(value)
            } else {
                ValueObject.Invalid
            }
        }
    }
}