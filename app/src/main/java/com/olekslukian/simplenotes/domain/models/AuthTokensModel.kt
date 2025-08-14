package com.olekslukian.simplenotes.domain.models

data class AuthTokensModel(
    val accessToken: String,
    val refreshToken: String
)
