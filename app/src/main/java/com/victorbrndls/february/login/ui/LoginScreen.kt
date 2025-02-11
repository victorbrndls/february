package com.victorbrndls.february.login.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victorbrndls.february.ui.theme.FebruaryTheme

/**
 * Create an Android app that simulates a login process using Kotlin coroutines. The app should have the following features:
 * Login form: Design a simple login form with fields for username and password.
 * Simulated authentication: Utilize Kotlin coroutines to simulate an authentication process. For example, you can use a coroutine with a delay to mimic the authentication process taking some time.
 * Display login result: After the simulated authentication, display a message indicating whether the login was successful or not.
 * Error handling: Handle cases where the login process encounters errors or invalid credentials gracefully, displaying appropriate error messages to the user.
 * Bonus (optional):
 *
 * Loading indicator: Show a loading indicator while the authentication process is ongoing.
 * Remember me: Add an option to remember the user's login credentials for future sessions.
 */

@Composable
fun LoginScreen(
	viewModel: LoginViewModel,
	innerPadding: PaddingValues,
) {
	val state by viewModel.state.collectAsState()

	LoginScreen(
		state = state,
		onUsernameChanged = viewModel::onUsernameChanged,
		onPasswordChanged = viewModel::onPasswordChanged,
		onToggleSaveCredentials = viewModel::onToggleSaveCredentials,
		onSubmit = viewModel::onSubmit,
		innerPadding = innerPadding
	)
}

@Composable
fun LoginScreen(
	state: LoginViewState,
	onUsernameChanged: (String) -> Unit,
	onPasswordChanged: (String) -> Unit,
	onToggleSaveCredentials: (Boolean) -> Unit,
	onSubmit: () -> Unit,
	innerPadding: PaddingValues,
) {
	Box(
		modifier = Modifier
			.padding(innerPadding)
			.consumeWindowInsets(innerPadding)
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
				.fillMaxSize()
				.padding(horizontal = 32.dp)
		) {
			Spacer(Modifier.height(64.dp))

			TextField(
				value = state.username,
				onValueChange = onUsernameChanged,
				label = { Text("Username") },
				modifier = Modifier.fillMaxWidth()
			)

			Spacer(Modifier.height(16.dp))

			TextField(
				value = state.password,
				onValueChange = onPasswordChanged,
				label = { Text("Password") },
				modifier = Modifier.fillMaxWidth()
			)

			Spacer(Modifier.height(8.dp))

			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.align(Alignment.End)
			) {
				Checkbox(state.saveCredentials, onCheckedChange = onToggleSaveCredentials)
				Text(
					text = "Save credentials",
				)
			}

			Button(
				onClick = onSubmit,
			) {
				Text(
					text = "Login"
				)
			}

			Spacer(Modifier.height(16.dp))

			if (state.isSuccess) {
				Text(
					text = "Login successful",
					color = Color.Green,
				)
			} else if (state.errorMessage != null) {
				Text(
					text = state.errorMessage,
					color = Color.Red,
				)
			}
		}

		AnimatedVisibility(
			visible = state.isLoading,
			modifier = Modifier.align(Alignment.Center)
		) {
			CircularProgressIndicator()
		}
	}
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
	FebruaryTheme {
		LoginScreen(
			state = LoginViewState(
				username = "username",
				password = "password",
				saveCredentials = false,
				isSuccess = false,
				errorMessage = null,
			),
			onUsernameChanged = {},
			onPasswordChanged = {},
			onToggleSaveCredentials = {},
			onSubmit = {},
			innerPadding = PaddingValues(top = 48.dp, bottom = 32.dp)
		)
	}
}

@Preview(showSystemUi = true)
@Composable
private fun Preview_Loading() {
	FebruaryTheme {
		LoginScreen(
			state = LoginViewState(
				username = "username",
				password = "password",
				saveCredentials = false,
				isSuccess = false,
				errorMessage = null,
				isLoading = true,
			),
			onUsernameChanged = {},
			onPasswordChanged = {},
			onToggleSaveCredentials = {},
			onSubmit = {},
			innerPadding = PaddingValues(top = 48.dp, bottom = 32.dp)
		)
	}
}

@Preview(showSystemUi = true)
@Composable
private fun Preview_Success() {
	FebruaryTheme {
		LoginScreen(
			state = LoginViewState(
				username = "username",
				password = "password",
				saveCredentials = false,
				isSuccess = true,
				errorMessage = null,
			),
			onUsernameChanged = {},
			onPasswordChanged = {},
			onToggleSaveCredentials = {},
			onSubmit = {},
			innerPadding = PaddingValues(top = 48.dp, bottom = 32.dp)
		)
	}
}

@Preview(showSystemUi = true)
@Composable
private fun Preview_Error() {
	FebruaryTheme {
		LoginScreen(
			state = LoginViewState(
				username = "username",
				password = "password",
				saveCredentials = false,
				isSuccess = false,
				errorMessage = "Something went wrong",
			),
			onUsernameChanged = {},
			onPasswordChanged = {},
			onToggleSaveCredentials = {},
			onSubmit = {},
			innerPadding = PaddingValues(top = 48.dp, bottom = 32.dp)
		)
	}
}