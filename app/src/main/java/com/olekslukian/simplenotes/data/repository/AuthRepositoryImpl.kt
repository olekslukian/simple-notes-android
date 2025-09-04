package com.olekslukian.simplenotes.data.repository

import com.olekslukian.simplenotes.core.architecture.getOrNull
import com.olekslukian.simplenotes.core.mappers.toAuthTokens
import com.olekslukian.simplenotes.core.mappers.toChangePasswordDto
import com.olekslukian.simplenotes.core.mappers.toLoginRequestDto
import com.olekslukian.simplenotes.core.mappers.toRegisterRequestDto
import com.olekslukian.simplenotes.data.network.AuthService
import com.olekslukian.simplenotes.data.network.TokenManager
import com.olekslukian.simplenotes.domain.models.AuthTokensModel
import com.olekslukian.simplenotes.domain.models.ChangePasswordModel
import com.olekslukian.simplenotes.domain.models.LoginModel
import com.olekslukian.simplenotes.domain.models.RegisterModel
import com.olekslukian.simplenotes.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val tokenManager: TokenManager
) : AuthRepository {
    override suspend fun register(registerModel: RegisterModel): Boolean {
       return authService.register(registerModel.toRegisterRequestDto())
    }

    override suspend fun login(loginModel: LoginModel): Boolean {
        val tokensResponse = authService.login(loginModel.toLoginRequestDto())
        val authTokens = tokensResponse.toAuthTokens()

        if (authTokens.isInvalid) {
            return false
        }

        val  accessToken = authTokens.accessToken.getOrNull
        val refreshToken = authTokens.refreshToken.getOrNull

        if (accessToken != null && refreshToken != null) {
            tokenManager.saveTokens(accessToken, refreshToken)
        }

        return true
    }

    override suspend fun refreshToken(refreshToken: String): Boolean {
        val result = authService.refreshToken(refreshToken)
            .toAuthTokens()

        return result.isValid
    }

    override suspend fun changePassword(changePasswordModel: ChangePasswordModel): Boolean {
        return authService.changePassword(changePasswordModel.toChangePasswordDto())
    }

    override suspend fun checkIfAuthenticated(): Boolean {
        return tokenManager.isAuthenticated()
    }
}