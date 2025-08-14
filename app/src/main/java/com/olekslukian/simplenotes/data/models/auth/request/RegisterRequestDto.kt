package com.olekslukian.simplenotes.data.models.auth.request

data class RegisterRequestDto(
    val email: String,
    val password: String,
    val passwordConfirmation: String
)
