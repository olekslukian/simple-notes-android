package com.olekslukian.simplenotes.domain.models

data class ChangePasswordModel(
    val oldPassword: String,
    val newPassword: String,
    val confirmNewPassword: String
)
