package com.victorbrndls.february.login.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
		onSubmit = viewModel::onSubmit,
		innerPadding = innerPadding
	)
}

@Composable
fun LoginScreen(
	state: LoginViewState,
	onUsernameChanged: (String) -> Unit,
	onPasswordChanged: (String) -> Unit,
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
		) {
			Spacer(Modifier.height(64.dp))

			TextField(
				value = state.username,
				onValueChange = onUsernameChanged,
				label = { Text("Username") }
			)

			Spacer(Modifier.height(16.dp))

			TextField(
				value = state.password,
				onValueChange = onPasswordChanged,
				label = { Text("Password") }
			)

			Spacer(Modifier.height(16.dp))

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
				isSuccess = false,
				errorMessage = null,
			),
			onUsernameChanged = {},
			onPasswordChanged = {},
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
				isSuccess = false,
				errorMessage = null,
				isLoading = true,
			),
			onUsernameChanged = {},
			onPasswordChanged = {},
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
				isSuccess = true,
				errorMessage = null,
			),
			onUsernameChanged = {},
			onPasswordChanged = {},
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
				isSuccess = false,
				errorMessage = "Something went wrong",
			),
			onUsernameChanged = {},
			onPasswordChanged = {},
			onSubmit = {},
			innerPadding = PaddingValues(top = 48.dp, bottom = 32.dp)
		)
	}
}