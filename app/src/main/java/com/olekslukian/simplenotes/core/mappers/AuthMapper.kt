package com.olekslukian.simplenotes.core.mappers

import com.olekslukian.simplenotes.data.models.auth.response.TokensResponseDto
import com.olekslukian.simplenotes.domain.models.AuthTokensModel


fun TokensResponseDto.toAuthTokens() : AuthTokensModel {
    return AuthTokensModel(
        accessToken = accessToken ?: "",
        refreshToken = refreshToken ?: ""
    )
}