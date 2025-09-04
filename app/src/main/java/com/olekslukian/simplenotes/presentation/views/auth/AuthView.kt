package com.olekslukian.simplenotes.presentation.views.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.olekslukian.simplenotes.core.architecture.getOr
import com.olekslukian.simplenotes.core.valueobjects.EmailValueObject
import com.olekslukian.simplenotes.core.valueobjects.NonEmptyStringValueObject
import com.olekslukian.simplenotes.core.valueobjects.PasswordValueObject
import com.olekslukian.simplenotes.presentation.views.auth.viewmodel.AuthEvent
import com.olekslukian.simplenotes.presentation.views.auth.viewmodel.AuthNavigationEvent
import com.olekslukian.simplenotes.presentation.views.auth.viewmodel.AuthStatus
import com.olekslukian.simplenotes.presentation.views.auth.viewmodel.AuthViewModel

@Composable
fun AuthView(
    onNavigateToHome: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
  val uiState =  viewModel.uiState.collectAsState().value
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
           if (event is AuthNavigationEvent.NavigateToHome)  {
                onNavigateToHome()
           }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Log in", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
               value = uiState.email.getOr("") ,
                onValueChange = {
                    viewModel.onEvent(AuthEvent.EmailChanged(NonEmptyStringValueObject(it)))
                },
                label = { Text("Email") },
                isError = uiState.emailError && !uiState.email.isValid,
                supportingText = {
                    if (uiState.emailError && !uiState.email.isValid) {
                        Text("Invalid email", color = MaterialTheme.colorScheme.error)
                    }
                },
                singleLine = true,
                enabled = uiState.authStatus != AuthStatus.LOADING,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.password.getOr(""),
                onValueChange = {
                    viewModel.onEvent(AuthEvent.PasswordChanged(NonEmptyStringValueObject(it))) },
                label = { Text("Password") },
                isError = uiState.passwordError && !uiState.password.isValid,
                supportingText = {
                    if (uiState.passwordError && !uiState.password.isValid) {
                        Text("Invalid password", color = MaterialTheme.colorScheme.error)
                    }
                },
                singleLine = true,
                enabled = uiState.authStatus != AuthStatus.LOADING,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        // TODO(olekslukian): Add normal icons for password visibility toggle
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.onEvent(AuthEvent.LoginEvent) },
                enabled = uiState.authStatus != AuthStatus.LOADING,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                if (uiState.authStatus == AuthStatus.LOADING) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Login")
                }
            }

        }
    }

    if (uiState.authStatus == AuthStatus.FAILURE && uiState.error.isValid) {
        AlertDialog(
            onDismissRequest = { viewModel.onEvent(AuthEvent.ErrorDismissed) },
            title = { Text("Login Error") },
            text = { Text(uiState.error.getOr("Something went wrong")) },
            confirmButton = {
                TextButton(onClick = { viewModel.onEvent(AuthEvent.ErrorDismissed) }) {
                    Text("OK")
                }
            }
        )
    }
}