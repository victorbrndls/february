package com.victorbrndls.february.login.domain

import com.victorbrndls.february.login.data.LoginCredentialsRepository
import javax.inject.Inject

class SaveLoginCredentialsUseCase @Inject constructor(
	private val repository: LoginCredentialsRepository,
) {

	operator fun invoke(credentials: LoginCredentials?) {
		return repository.saveCredentials(credentials)
	}

}