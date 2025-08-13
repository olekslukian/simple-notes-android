package com.olekslukian.simplenotes.data.models.auth.response

data class TokensResponseDto(
    val accessToken: String,
    val refreshToken: String
)