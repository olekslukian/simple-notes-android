package com.olekslukian.simplenotes.domain.models

data class RegisterModel(
    val email: String,
    val password: String,
    val confirmPassword: String
)
