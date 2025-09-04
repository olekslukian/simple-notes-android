package com.olekslukian.simplenotes.domain.usecase

import android.util.Log
import com.olekslukian.simplenotes.core.Result
import com.olekslukian.simplenotes.domain.models.AuthTokensModel
import com.olekslukian.simplenotes.domain.models.LoginModel
import com.olekslukian.simplenotes.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(loginModel: LoginModel): Flow<Result<Boolean>> = flow {
        emit(Result.Loading())
        try {
            emit(Result.Success(authRepository.login(loginModel)))
        } catch (e: Exception) {
            Log.e("LoginUseCase", "Error logging in", e)
            emit(Result.Error(message = "LogInUseCase: ${e.message}"))
        }
    }
}