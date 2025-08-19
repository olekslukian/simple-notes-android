package com.olekslukian.simplenotes.domain.repository

import com.olekslukian.simplenotes.domain.models.AuthTokensModel
import com.olekslukian.simplenotes.domain.models.ChangePasswordModel
import com.olekslukian.simplenotes.domain.models.LoginModel
import com.olekslukian.simplenotes.domain.models.RegisterModel

interface AuthRepository {
    suspend fun register(registerModel: RegisterModel) : Boolean
    suspend fun login(loginModel: LoginModel) : AuthTokensModel
    suspend fun refreshToken(refreshToken: String) : AuthTokensModel
    suspend fun changePassword(changePasswordModel: ChangePasswordModel) : Boolean
}