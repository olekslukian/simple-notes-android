package com.olekslukian.simplenotes.data.models.auth.request

data class LoginRequestDto(
    val email: String,
    val password: String
)
