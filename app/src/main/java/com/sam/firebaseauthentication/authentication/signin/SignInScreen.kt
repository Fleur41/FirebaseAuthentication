
package com.sam.firebaseauthentication.authentication.signin

// ... other necessary imports from your SignInScreen.kt (AnimatedVisibility, Icons, etc.)
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sam.firebaseauthentication.R
import com.sam.firebaseauthentication.authentication.signup.AuthViewModel
import com.sam.firebaseauthentication.authentication.signup.CompanyInfo
import com.sam.firebaseauthentication.authentication.signup.EmailAndPasswordContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    // Consider adding a ViewModel parameter for state and logic
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Sign In") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            // horizontalAlignment = Alignment.CenterHorizontally, // Optional: for centering the whole column
            // verticalArrangement = Arrangement.Center      // Optional: for centering the whole column
        ) {
            CompanyInfo(
                modifier = Modifier
                    .weight(1f) // Takes available space
                    .fillMaxWidth() // Ensures content within CompanyInfo can align if needed
            )

            EmailAndPasswordContent(
                modifier = Modifier
                    .weight(1f) // Takes available space
                    .padding(horizontal = 16.dp), // Padding for the content itself
                email = email,
                password = password,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onEmailClear = { email = "" },
                onPasswordClear = { password = "" },
                actionButtonContent = {
                    Text(text = "Sign In")
                },

                onActionButtonClick = {
                    // TODO: Implement Sign In Logic (e.g., call ViewModel)
                    authViewModel.signIn(email, password)
                }
                // Intentionally OMITTING confirm password parameters.
                // showConfirmPasswordField defaults to false.
            )

            SignUpBox(
                modifier = Modifier
                    .weight(1f) // Takes available space
                    .fillMaxWidth(), // Ensures content within SignUpBox can align if needed
                onSignUpClick = onSignUpClick
            )
        }
    }
}

@Composable
fun SignUpBox(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit
) {
    Box (
        modifier = modifier
            .fillMaxSize() // Fills the weighted space
            .padding(bottom = 16.dp), // Padding for the text from the bottom of the box
        contentAlignment = Alignment.BottomCenter // Aligns the Text to the bottom center
    ){
        Text(
            modifier = Modifier.clickable { onSignUpClick() },
            text = "Register now",
            style = MaterialTheme.typography.titleMedium,
            textDecoration = TextDecoration.Underline,
            color = Color.Blue // Or your MaterialTheme.colorScheme.primary
        )
    }
}

// Definition of CustomTextField - should be in a shared location or here if only used by SignInScreen
// This is copied from your provided SignInScreen.kt
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    isPasswordField: Boolean = false,
    onClear: () -> Unit,
) {
    var showPassword by remember { mutableStateOf(false) }
    // Ensure you have these drawables in your res/drawable folder
    val passwordIconResId = if (showPassword) R.drawable.ic_eye_filled else R.drawable.ic_eye_outlined
    // If you don't have these specific icons, replace them or remove password visibility toggle for now.
    // For example, use Icons.Filled.Visibility and Icons.Filled.VisibilityOff from androidx.compose.material.icons.filled

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholderText) },
        shape = RoundedCornerShape(16.dp),
        visualTransformation = if (isPasswordField && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (value.isNotEmpty()) { // Show icon only if there's text
                if (isPasswordField) {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            painter = painterResource(id = passwordIconResId),
                            contentDescription = if (showPassword) "Hide password" else "Show password"
                        )
                    }
                } else {
                    IconButton(onClick = onClear) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            }
        }
    )
}