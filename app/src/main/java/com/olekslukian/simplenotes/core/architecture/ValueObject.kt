package com.olekslukian.simplenotes.core.architecture

sealed class ValueObject<out T> : IValueObject<T> {
    data class Valid<out T>(override val value: T) : ValueObject<T>() {
       override val isValid: Boolean = true
    }

    object Invalid : ValueObject<Nothing>() {
        override val value: Nothing? = null
        override val isValid: Boolean = false
    }

    override val isValid get() = when (this) {
        is Valid -> true
        is Invalid -> false
    }

    companion object {
        operator fun <T> invoke(value: T?) : ValueObject<T> {
            val validatedValue = validate(value)

            return if (validatedValue != null) {
                Valid(validatedValue)
            } else {
                Invalid
            }
        }

        fun <T> invalid(): ValueObject<T> = Invalid

        private fun <T> validate(value: T?) : T?{
            return value;
        }

    }
}

fun <T> ValueObject<T>.getOr(fallback: T): T {
    return when (this) {
        is ValueObject.Valid -> this.value
        is ValueObject.Invalid -> fallback
    }
}

val <T> ValueObject<T>.getOrNull : T?
    get() = when (this) {
        is ValueObject.Valid -> value
        is ValueObject.Invalid -> null
    }