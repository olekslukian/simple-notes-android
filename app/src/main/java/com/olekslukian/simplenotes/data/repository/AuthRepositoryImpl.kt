package com.olekslukian.simplenotes.data.repository

import com.olekslukian.simplenotes.core.mappers.toAuthTokens
import com.olekslukian.simplenotes.core.mappers.toChangePasswordDto
import com.olekslukian.simplenotes.core.mappers.toLoginRequestDto
import com.olekslukian.simplenotes.core.mappers.toRegisterRequestDto
import com.olekslukian.simplenotes.data.network.AuthService
import com.olekslukian.simplenotes.domain.models.AuthTokensModel
import com.olekslukian.simplenotes.domain.models.ChangePasswordModel
import com.olekslukian.simplenotes.domain.models.LoginModel
import com.olekslukian.simplenotes.domain.models.RegisterModel
import com.olekslukian.simplenotes.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService ) : AuthRepository {
    override suspend fun register(registerModel: RegisterModel): Boolean {
       return authService.register(registerModel.toRegisterRequestDto())
    }

    override suspend fun login(loginModel: LoginModel): AuthTokensModel {
        return authService.login(loginModel.toLoginRequestDto())
            .toAuthTokens()
    }

    override suspend fun refreshToken(refreshToken: String): AuthTokensModel {
        return authService.refreshToken(refreshToken)
            .toAuthTokens()
    }

    override suspend fun changePassword(changePasswordModel: ChangePasswordModel): Boolean {
        return authService.changePassword(changePasswordModel.toChangePasswordDto())
    }
}