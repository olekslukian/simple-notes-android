package com.olekslukian.simplenotes.domain.usecase

import com.olekslukian.simplenotes.core.Result
import com.olekslukian.simplenotes.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckIfAuthenticatedUseCase @Inject constructor(private val authRepository: AuthRepository){
    operator fun invoke() : Flow<Result<Boolean>> = flow {
        emit(Result.Loading())
        try {
            emit(Result.Success(authRepository.checkIfAuthenticated()))
        } catch (e: Exception) {
            emit(Result.Error(message = "CheckIfAuthenticatedUseCase: ${e.message}"))
        }
    }
}