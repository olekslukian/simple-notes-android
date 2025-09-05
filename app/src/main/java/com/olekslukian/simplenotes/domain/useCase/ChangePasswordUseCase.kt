package com.olekslukian.simplenotes.domain.useCase

import android.util.Log
import com.olekslukian.simplenotes.core.Result
import com.olekslukian.simplenotes.domain.models.ChangePasswordModel
import com.olekslukian.simplenotes.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(private val authRepository: AuthRepository){
    operator fun invoke(changePasswordModel: ChangePasswordModel) : Flow<Result<Boolean>> = flow {
        emit(Result.Loading())
        try {
            emit(Result.Success(authRepository.changePassword(changePasswordModel)))
        } catch (e: Exception) {
            Log.e("ChangePasswordUseCase", "Error changing password", e)
            emit(Result.Error(message = "ChangePasswordUseCase: ${e.message}"))
        }
    }
}