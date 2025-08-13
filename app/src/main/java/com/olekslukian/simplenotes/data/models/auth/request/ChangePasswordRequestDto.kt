package com.olekslukian.simplenotes.data.models.auth.request

data class ChangePasswordRequestDto(
    val oldPassword: String,
    val newPassword: String,
    val newPasswordConfirmation: String
)