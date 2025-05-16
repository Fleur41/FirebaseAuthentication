package com.sam.firebaseauthentication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sam.firebaseauthentication.authentication.signin.SignInScreen
import com.sam.firebaseauthentication.authentication.signup.SignUpScreen

@Composable
fun FirebaseAppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationDestination.SignIn.route

    ){
        composable(route = NavigationDestination.SignIn.route){
            SignInScreen(
                onSignUpClick = {
                    navController.navigate(NavigationDestination.SignUp.route)
                }
            )
        }
        composable(route = NavigationDestination.SignUp.route){
            SignUpScreen(
                onBack = {
                    navController.popBackStack()
                }
            )

        }
    }
}