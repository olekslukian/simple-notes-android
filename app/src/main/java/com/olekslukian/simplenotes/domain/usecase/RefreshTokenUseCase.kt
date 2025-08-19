package com.olekslukian.simplenotes.domain.usecase

import com.olekslukian.simplenotes.core.Result
import com.olekslukian.simplenotes.domain.models.AuthTokensModel
import com.olekslukian.simplenotes.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(private val authRepository: AuthRepository){
    operator fun invoke(refreshToken: String) : Flow<Result<AuthTokensModel>> = flow {
        emit(Result.Loading())
        try {
            emit(Result.Success(authRepository.refreshToken(refreshToken)))
        } catch (e: Exception) {
            emit(Result.Error(message = "RefreshTokenUseCase: ${e.message}"))
        }
    }
}