package com.olekslukian.simplenotes.data.models.auth.request

data class RegisterDto(
    val email: String,
    val password: String,
    val passwordConfirmation: String
)
