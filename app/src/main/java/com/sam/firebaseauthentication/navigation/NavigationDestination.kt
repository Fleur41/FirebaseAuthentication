package com.sam.firebaseauthentication.navigation

sealed interface NavigationDestination {
    val title: String
    val route: String

    data object SignIn: NavigationDestination {
        override val title: String
            get() = "Sign In"
        override val route: String
            get() = "sign_in"
    }

    data object SignUp: NavigationDestination {
        override val title: String
            get() = "Sign Up"
        override val route: String
            get() = "sign_up"
    }

    data object Home: NavigationDestination {
        override val title: String
            get() = "Home"
        override val route: String
            get() = "home"
    }
}