package com.olekslukian.simplenotes.domain.usecase

import android.util.Log
import com.olekslukian.simplenotes.core.Result
import com.olekslukian.simplenotes.domain.models.RegisterModel
import com.olekslukian.simplenotes.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository){
    operator fun invoke(registerModel: RegisterModel) : Flow<Result<Boolean>> = flow {
       emit(Result.Loading())
        try {
            emit(Result.Success(authRepository.register(registerModel)))
        } catch (e: Exception) {
            Log.e("RegisterUseCase", "Error registering user", e)
            emit(Result.Error(message =  "RegisterUseCase: ${e.message}"))
        }
    }
}