package com.victorbrndls.february.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.february.login.domain.LoginUseCase
import com.victorbrndls.february.login.domain.LoginUseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val loginUseCase: LoginUseCase,
) : ViewModel() {

	private val _state = MutableStateFlow(
		LoginViewState(
			username = "",
			password = "",
			saveCredentials = false,
			isSuccess = false,
			errorMessage = null,
		)
	)

	val state = _state.stateIn(
		viewModelScope,
		SharingStarted.WhileSubscribed(5_000),
		_state.value,
	)

	fun onUsernameChanged(username: String) {
		_state.update { it.copy(username = username) }
	}

	fun onPasswordChanged(password: String) {
		_state.update { it.copy(password = password) }
	}

	fun onToggleSaveCredentials(value: Boolean) {
		_state.update { it.copy(saveCredentials = value) }
	}

	fun onSubmit() {
		if (state.value.username.isEmpty() || state.value.password.isEmpty()) {
			_state.update { it.copy(errorMessage = "Username and password are required") }
			return
		}

		_state.update { it.copy(isSuccess = false, errorMessage = null, isLoading = true) }

		viewModelScope.launch {
			val result = loginUseCase(
				username = state.value.username,
				password = state.value.password,
			)

			when (result) {
				is LoginUseCaseResult.Success -> {
					_state.update {
						it.copy(isSuccess = true, isLoading = false)
					}
				}
				is LoginUseCaseResult.Failure -> {
					_state.update {
						it.copy(errorMessage = result.error, isLoading = false)
					}
				}
			}
		}
	}

}