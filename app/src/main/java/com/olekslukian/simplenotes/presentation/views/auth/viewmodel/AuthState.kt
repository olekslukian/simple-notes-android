package com.olekslukian.simplenotes.presentation.views.auth.viewmodel

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val isLoading: Boolean = false,
    val error: String = ""
)