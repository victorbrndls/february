package com.victorbrndls.february.login.data

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.victorbrndls.february.login.domain.LoginCredentials
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoginCredentialsRepository @Inject constructor(
	@ApplicationContext context: Context,
) {

	private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

	private val sharedPreferences = EncryptedSharedPreferences.create(
		"login_credentials",
		masterKeyAlias,
		context,
		EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
		EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
	)

	fun getCredentials(): LoginCredentials? {
		try {
			val username = sharedPreferences.getString("username", null)
			val password = sharedPreferences.getString("password", null)

			if (username != null && password != null) {
				return LoginCredentials(username, password)
			}
		} catch (e: Exception) {
			Log.e("LCR", "Error getting credentials", e)
		}
		return null
	}

	fun saveCredentials(credentials: LoginCredentials?) {
		if (credentials == null) {
			sharedPreferences.edit().clear().apply()
			return
		}

		sharedPreferences.edit {
			putString("username", credentials.username)
			putString("password", credentials.password)
		}
	}

}