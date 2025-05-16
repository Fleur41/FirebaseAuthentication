
package com.sam.firebaseauthentication.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry
import com.sam.firebaseauthentication.Utils.Constants // Make sure this path is correct

/**
 *  Custom enter transition that slides in from the right.
 *  This function is intended to be used as an enterTransition.
 */
fun AnimatedContentTransitionScope<NavBackStackEntry>.slideIntoContainerAnimation(): EnterTransition {
    return slideInHorizontally(
        animationSpec = tween(
            durationMillis = Constants.NAVIGATION_ANIMATION_DURATION,
            easing = EaseIn
        ),
        initialOffsetX = { fullWidth -> fullWidth } // Slide in from the right
    )
}

/**
 *  Custom exit transition that slides out to the left.
 *  This function is intended to be used as an exitTransition.
 */
fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutOfContainerAnimation(): ExitTransition {
    return slideOutHorizontally(
        animationSpec = tween(
            durationMillis = Constants.NAVIGATION_ANIMATION_DURATION,
            easing = EaseOut
        ),
        targetOffsetX = { fullWidth -> -fullWidth } // Slide out to the left
    )
}


/**
 *  Custom enter transition with configurable slide direction.
 */
fun AnimatedContentTransitionScope<NavBackStackEntry>.slideInWithDirection(
    slideDirection: AnimatedContentTransitionScope.SlideDirection = AnimatedContentTransitionScope.SlideDirection.End
): EnterTransition {
    return slideInHorizontally(
        animationSpec = tween(
            durationMillis = Constants.NAVIGATION_ANIMATION_DURATION,
            easing = EaseIn
        ),
        initialOffsetX = { fullWidth ->
            when (slideDirection) {
                AnimatedContentTransitionScope.SlideDirection.Start -> -fullWidth // From Left
                AnimatedContentTransitionScope.SlideDirection.End -> fullWidth    // From Right
                else -> fullWidth // Default to from Right
            }
        }
    )
}

/**
 *  Custom exit transition with configurable slide direction.
 */
fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutWithDirection(
    slideDirection: AnimatedContentTransitionScope.SlideDirection = AnimatedContentTransitionScope.SlideDirection.Start
): ExitTransition {
    return slideOutHorizontally(
        animationSpec = tween(
            durationMillis = Constants.NAVIGATION_ANIMATION_DURATION,
            easing = EaseOut
        ),
        targetOffsetX = { fullWidth ->
            when (slideDirection) {
                AnimatedContentTransitionScope.SlideDirection.Start -> -fullWidth // To Left
                AnimatedContentTransitionScope.SlideDirection.End -> fullWidth    // To Right
                else -> -fullWidth // Default to towards Left
            }
        }
    )
}