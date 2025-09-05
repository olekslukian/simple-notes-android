package com.olekslukian.simplenotes.presentation.views.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olekslukian.simplenotes.core.Result
import com.olekslukian.simplenotes.core.architecture.ValueObject
import com.olekslukian.simplenotes.domain.models.RegisterModel
import com.olekslukian.simplenotes.domain.useCase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<RegisterNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged -> {

                _uiState.value = _uiState.value.copy(
                    email = event.email,
                )
            }
            is RegisterEvent.PasswordChanged -> {

                _uiState.value = _uiState.value.copy(
                    password = event.password,
                )
            }
            is RegisterEvent.PasswordConfirmationChanged -> {

                _uiState.value = _uiState.value.copy(
                    passwordConfirmation = event.passwordConfirmation,
                )
            }
            is RegisterEvent.RegistrationEvent -> {
                register()
            }
            is RegisterEvent.ErrorDismissed -> {
                _uiState.value = _uiState.value.copy(error = ValueObject.invalid())
            }
        }
    }

    private fun register() {
        val currentState = _uiState.value

        if (!currentState.email.isValid || !currentState.password.isValid || !currentState.passwordConfirmation.isValid) {
            _uiState.value = currentState.copy(
                emailError = !currentState.email.isValid,
                passwordError = !currentState.password.isValid,
                passwordConfirmationError = !currentState.passwordConfirmation.isValid && currentState.passwordConfirmation.value != currentState.password.value,
            )

            return
        }

        viewModelScope.launch {
            _uiState.value = currentState.copy(registerStatus = RegisterStatus.LOADING)

           val registerModel = RegisterModel(
               email = currentState.email,
               password = currentState.password,
               passwordConfirmation = currentState.passwordConfirmation
           )

            registerUseCase(registerModel).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.value = currentState.copy(registerStatus = RegisterStatus.LOADING)
                    }
                    is Result.Success -> {
                        _uiState.value = currentState.copy(registerStatus = RegisterStatus.SUCCESS)
                        _navigationEvent.emit(RegisterNavigationEvent.NavigateToLogin)
                    }
                    is Result.Error -> {
                        _uiState.value = currentState.copy(
                            registerStatus = RegisterStatus.FAILURE,
                            error = ValueObject(result.message ?: "Registration failed. Please try again.")
                        )
                    }
                }

            }
        }
    }
}