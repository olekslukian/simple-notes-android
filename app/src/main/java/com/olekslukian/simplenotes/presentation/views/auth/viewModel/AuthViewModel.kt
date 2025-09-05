package com.olekslukian.simplenotes.presentation.views.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olekslukian.simplenotes.core.Result
import com.olekslukian.simplenotes.core.architecture.ValueObject
import com.olekslukian.simplenotes.domain.models.LoginModel
import com.olekslukian.simplenotes.domain.useCase.LoginUseCase
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

                _uiState.value = _uiState.value.copy(
                    email = event.email,
                )
            }
            is AuthEvent.PasswordChanged -> {

                _uiState.value = _uiState.value.copy(
                    password = event.password,
                )
            }
            is AuthEvent.LoginEvent -> {
                login()
            }
            is AuthEvent.ErrorDismissed -> {
               _uiState.value = _uiState.value.copy(error = ValueObject.invalid())
            }
        }
    }

    private fun login() {
       val currentState = _uiState.value

        if (!currentState.email.isValid || !currentState.password.isValid) {
            _uiState.value = currentState.copy(
                emailError = !currentState.email.isValid,
                passwordError = !currentState.password.isValid,
            )

            return
        }

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