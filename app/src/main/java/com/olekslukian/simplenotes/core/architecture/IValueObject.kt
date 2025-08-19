package com.olekslukian.simplenotes.core.architecture

interface IValueObject<out T> : IValidable {
    val value: T?
}



