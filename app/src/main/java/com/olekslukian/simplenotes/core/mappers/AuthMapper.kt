package com.olekslukian.simplenotes.core.mappers

import com.olekslukian.simplenotes.core.architecture.ValueObject
import com.olekslukian.simplenotes.core.architecture.getOr
import com.olekslukian.simplenotes.data.models.auth.request.ChangePasswordRequestDto
import com.olekslukian.simplenotes.data.models.auth.request.LoginRequestDto
import com.olekslukian.simplenotes.data.models.auth.request.RegisterRequestDto
import com.olekslukian.simplenotes.data.models.auth.response.TokensResponseDto
import com.olekslukian.simplenotes.domain.models.AuthTokensModel
import com.olekslukian.simplenotes.domain.models.ChangePasswordModel
import com.olekslukian.simplenotes.domain.models.LoginModel
import com.olekslukian.simplenotes.domain.models.RegisterModel


fun TokensResponseDto.toAuthTokens() : AuthTokensModel {
    return AuthTokensModel(
        accessToken = ValueObject(accessToken),
        refreshToken = ValueObject(refreshToken),
    )
}

fun ChangePasswordModel.toChangePasswordDto() : ChangePasswordRequestDto {
    return ChangePasswordRequestDto(
        oldPassword = oldPassword.getOr(""),
        newPassword = newPassword.getOr(""),
        newPasswordConfirmation = newPasswordConfirmation.getOr("")
    )
}

fun LoginModel.toLoginRequestDto() : LoginRequestDto {
    return LoginRequestDto(
        email = email.getOr(""),
        password = password.getOr("")
    )
}

fun RegisterModel.toRegisterRequestDto() : RegisterRequestDto {
    return RegisterRequestDto(
        email = email.getOr(""),
        password = password.getOr(""),
        passwordConfirmation = passwordConfirmation.getOr("")
    )
}