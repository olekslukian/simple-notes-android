package com.olekslukian.simplenotes.domain.di

import com.olekslukian.simplenotes.domain.repository.AuthRepository
import com.olekslukian.simplenotes.domain.useCase.ChangePasswordUseCase
import com.olekslukian.simplenotes.domain.useCase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun registerUseCaseProvider(authRepository: AuthRepository) : RegisterUseCase {
        return RegisterUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun loginUseCaseProvider(authRepository: AuthRepository) : RegisterUseCase {
       return  RegisterUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun refreshTokenUseCaseProvider(authRepository: AuthRepository) : RegisterUseCase {
        return RegisterUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun changePasswordUseCaseProvider(authRepository: AuthRepository) : ChangePasswordUseCase {
        return ChangePasswordUseCase(authRepository)
    }
}