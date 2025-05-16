
package com.sam.firebaseauthentication.authentication.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onBack: () -> Unit,
    // Consider adding a ViewModel parameter for state and logic
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") } // State for the confirm password field

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Sign Up") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back" // Important for accessibility
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CompanyInfo(
                modifier = Modifier.weight(1f) // Takes available top space
            )

            EmailAndPasswordContent(
                modifier = Modifier
                    .weight(1f) // Takes available middle space
                    .padding(horizontal = 16.dp), // Padding for the content
                email = email,
                password = password,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onEmailClear = { email = "" },
                onPasswordClear = { password = "" },
                actionButtonText = "Sign Up",
                onActionButtonClick = {
                    authViewModel.signUp(email, password)
                },
                // --- Explicitly enable and provide values for Confirm Password ---
                showConfirmPasswordField = true,               // VERY IMPORTANT
                confirmPasswordValue = confirmPassword,
                onConfirmPasswordChange = { confirmPassword = it },
                onConfirmPasswordClear = { confirmPassword = "" }
            )

            Box(
                modifier = Modifier.weight(1f) // Takes available bottom space (acts as a spacer)
            )
        }
    }
}
