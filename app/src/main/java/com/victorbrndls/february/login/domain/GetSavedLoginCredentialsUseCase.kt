package com.victorbrndls.february.login.domain

import com.victorbrndls.february.login.data.LoginCredentialsRepository
import javax.inject.Inject

class GetSavedLoginCredentialsUseCase @Inject constructor(
	private val repository: LoginCredentialsRepository,
) {

	operator fun invoke(): LoginCredentials? {
		return repository.getCredentials()
	}

}