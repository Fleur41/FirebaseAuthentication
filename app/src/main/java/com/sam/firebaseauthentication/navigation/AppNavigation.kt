package com.sam.firebaseauthentication.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sam.firebaseauthentication.authentication.signin.SignInScreen
import com.sam.firebaseauthentication.authentication.signup.SignUpScreen
import com.sam.firebaseauthentication.components.slideIntoContainerAnimation
import com.sam.firebaseauthentication.components.slideOutOfContainerAnimation
import com.sam.firebaseauthentication.home.HomeScreen
import com.sam.firebaseauthentication.splash.SplashScreen

@OptIn(ExperimentalAnimationApi::class) // May be needed for NavHost/composable transitions
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    startDestination: NavigationDestination
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route
        // No global transitions if defining per-composable and your Nav version supports it
    ) {
        composable(
            route = NavigationDestination.SignIn.route,
            enterTransition = { slideIntoContainerAnimation() }, // This is your new function from Animations.kt

            exitTransition = { slideOutOfContainerAnimation() } // This is your new function from Animations.kt


        ) { backStackEntry ->
            SignInScreen(
                authViewModel =  hiltViewModel(backStackEntry),
                onSignUpClick = {
                    navController.navigate(NavigationDestination.SignUp.route)
                }
            )
        }

        composable(
            route = NavigationDestination.SignUp.route,
            enterTransition = { slideIntoContainerAnimation(towards = SlideDirection.Left) },
            exitTransition = { slideOutOfContainerAnimation(towards = SlideDirection.Right) }
            // For example:
            // popEnterTransition = { slideInWithDirection(SlideDirection.Start) }, // If SignUp could pop to a deeper screen
            // popExitTransition = { slideOutWithDirection(SlideDirection.End) }   // When SignUp is popped (e.g. back to SignIn)
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {navController.getBackStackEntry(NavigationDestination.SignIn.route)}
            SignUpScreen(
                authViewModel = hiltViewModel(parentEntry),
                onBack = {
                    navController.popBackStack()
                },
//                onSignUpSuccess = {
//                    navController.navigate(NavigationDestination.Home.route)
//                }
            )
        }

        composable(
            route = NavigationDestination.Home.route,
            enterTransition = { slideIntoContainerAnimation(towards = SlideDirection.Left) },
            exitTransition = { slideOutOfContainerAnimation(towards = SlideDirection.Right) }
            // For example:
            // popEnterTransition = { slideInWithDirection(SlideDirection.Start) }, // If SignUp could pop to a deeper screen
            // popExitTransition = { slideOutWithDirection(SlideDirection.End) }   // When SignUp is popped (e.g. back to SignIn)
        ) {
            HomeScreen()
        }

        composable(
            route = NavigationDestination.Splash.route,
//
            // For example:
            // popEnterTransition = { slideInWithDirection(SlideDirection.Start) }, // If SignUp could pop to a deeper screen
            // popExitTransition = { slideOutWithDirection(SlideDirection.End) }   // When SignUp is popped (e.g. back to SignIn)
        ) {
            SplashScreen()
        }
    }
}