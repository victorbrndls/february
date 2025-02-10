package com.victorbrndls.february

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.victorbrndls.february.login.ui.LoginScreen
import com.victorbrndls.february.login.ui.LoginViewModel
import com.victorbrndls.february.ui.theme.FebruaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			FebruaryTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					val viewModel by viewModels<LoginViewModel>()

					LoginScreen(
						viewModel = viewModel,
						innerPadding = innerPadding
					)
				}
			}
		}
	}
}
