package com.sam.firebaseauthentication

import androidx.compose.runtime.Composable
import com.sam.firebaseauthentication.navigation.FirebaseAppNavigation
import com.sam.firebaseauthentication.ui.theme.FirebaseAuthenticationTheme

@Composable
fun FirebaseApp() {
    FirebaseAuthenticationTheme {
        FirebaseAppNavigation()

    }
}