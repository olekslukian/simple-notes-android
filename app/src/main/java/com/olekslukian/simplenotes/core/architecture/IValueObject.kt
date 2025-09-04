package com.olekslukian.simplenotes.core.architecture

interface IValueObject<out T> : IValidable {
    val value: T?
}


fun <T> IValueObject<T>.getOr(fallback: T): T {
    return value ?: fallback
}

val <T> IValueObject<T>.getOrNull: T?
    get() = value
