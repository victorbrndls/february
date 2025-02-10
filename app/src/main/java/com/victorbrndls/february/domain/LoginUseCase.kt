package com.victorbrndls.february.domain

import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginUseCase @Inject constructor() {

	suspend operator fun invoke(username: String, password: String): LoginUseCaseResult {
		delay(2_000)

		if (password == "admin") {
			return LoginUseCaseResult.Success("token123")
		}

		return LoginUseCaseResult.Failure("Something went wrong")
	}

}

sealed interface LoginUseCaseResult {

	data class Success(val token: String) : LoginUseCaseResult

	data class Failure(val error: String) : LoginUseCaseResult

}