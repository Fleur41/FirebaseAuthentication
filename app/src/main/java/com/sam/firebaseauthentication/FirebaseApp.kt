package com.sam.firebaseauthentication

import androidx.compose.runtime.Composable
import com.sam.firebaseauthentication.navigation.AppNavigation
import com.sam.firebaseauthentication.ui.theme.FirebaseAuthenticationTheme

@Composable
fun FirebaseApp() {
    FirebaseAuthenticationTheme {
        AppNavigation()

    }
}