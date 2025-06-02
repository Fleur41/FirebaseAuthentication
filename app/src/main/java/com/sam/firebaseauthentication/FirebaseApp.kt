package com.sam.firebaseauthentication

import androidx.compose.runtime.Composable
import com.sam.firebaseauthentication.navigation.AppNavigation
import com.sam.firebaseauthentication.navigation.NavigationDestination
import com.sam.firebaseauthentication.ui.theme.FirebaseAuthenticationTheme

@Composable
fun FirebaseApp(
    startDestination: NavigationDestination
) {
    FirebaseAuthenticationTheme {
        AppNavigation(startDestination = startDestination)

    }
}