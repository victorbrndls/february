package com.victorbrndls.february.login.ui

data class LoginViewState(
	val username: String,
	val password: String,
	val saveCredentials: Boolean,
	val isSuccess: Boolean,
	val errorMessage: String?,
	val isLoading: Boolean = false,
)