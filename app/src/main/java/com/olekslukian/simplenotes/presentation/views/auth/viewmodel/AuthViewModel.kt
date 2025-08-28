package com.olekslukian.simplenotes.presentation.views.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olekslukian.simplenotes.core.Result
import com.olekslukian.simplenotes.core.architecture.ValueObject
import com.olekslukian.simplenotes.core.valueobjects.EmailValueObject
import com.olekslukian.simplenotes.core.valueobjects.PasswordValueObject
import com.olekslukian.simplenotes.domain.models.LoginModel
import com.olekslukian.simplenotes.domain.usecase.LoginUseCase
import com.olekslukian.simplenotes.utils.RegExp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val loginIUseCase: LoginUseCase): ViewModel() {
    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<AuthNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onEvent(event: AuthEvent)  {
        when (event) {
            is AuthEvent.EmailChanged -> {
                val emailVO = EmailValueObject(event.email)

                _uiState.value = _uiState.value.copy(
                    email = emailVO,
                    isEmailValid = emailVO.isValid ,
                    isLoginEnabled = emailVO.isValid && _uiState.value.password.isValid
                )
            }
            is AuthEvent.PasswordChanged -> {
                val passwordVO = PasswordValueObject(event.password)

                _uiState.value = _uiState.value.copy(
                    password = passwordVO,
                    isPasswordValid = passwordVO.isValid,
                    isLoginEnabled = _uiState.value.isEmailValid && passwordVO.isValid
                )
            }
            is AuthEvent.LoginEvent -> {
                login()
            }
            is AuthEvent.ErrorDismissed -> {
               _uiState.value.copy(error = ValueObject.invalid())
            }
        }
    }

    private fun login() {
       val currentState = _uiState.value
       if (!currentState.isLoginEnabled) return

        viewModelScope.launch {
            _uiState.value = currentState.copy(authStatus = AuthStatus.LOADING)

            val loginModel = LoginModel(
                email = currentState.email,
                password = currentState.password
            )

            loginIUseCase(loginModel).collect { result ->
                when(result) {
                    is Result.Loading -> {
                        _uiState.value = _uiState.value.copy(authStatus = AuthStatus.LOADING)
                    }
                    is Result.Success -> {
                       _uiState.value = _uiState.value.copy(authStatus = AuthStatus.SUCCESS)
                        _navigationEvent.emit(AuthNavigationEvent.NavigateToHome)
                    }
                    is Result.Error -> {
                        _uiState.value = _uiState.value.copy(
                            authStatus = AuthStatus.FAILURE,
                            error = ValueObject(result.message ?: "Login failed. Please try again")
                        )
                    }
                }

            }
        }
    }
}