package com.sam.firebaseauthentication.Settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.firebaseauthentication.authentication.AuthRepository
import com.sam.firebaseauthentication.datastore.DatastoreRepository
import com.sam.firebaseauthentication.navigation.NavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel // This is correct
class SettingsViewmodel @Inject constructor(
    private val authRepository: AuthRepository,
    private val datastoreRepository: DatastoreRepository // This dependency should now be correctly provided by Hilt
) : ViewModel() {
    private val _startDestination =
        MutableStateFlow<NavigationDestination>(NavigationDestination.Splash)
    val startDestination: StateFlow<NavigationDestination> get() = _startDestination

    // Expose the authentication state directly from the repository if needed elsewhere,
    // or keep it internal if only used for startDestination.
    // This is already good for observing in UI if needed.
    private val isAuthenticated: StateFlow<Boolean> = datastoreRepository.authenticated
        .catch { e ->
            // Handle errors from the datastoreRepository.authenticated Flow
            Log.e("SettingsViewModel", "Error collecting authentication state from DataStore", e)
            emit(false) // Default to not authenticated on error
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly, // Or Lazily, depending on when you need it to start collecting
            initialValue = false // Initial value before the flow emits its first item
        )

    init {
        viewModelScope.launch {
            delay(2000)
            // Observe the already transformed isAuthenticated StateFlow
            isAuthenticated.collect { authenticated ->
                _startDestination.value =
                    if (authenticated) NavigationDestination.Home else NavigationDestination.SignIn
                Log.d("SettingsViewModel", "Start destination updated: ${_startDestination.value}")
            }
        }
    }


    fun saveIsAuthenticated(authenticated: Boolean) {
        viewModelScope.launch {
            try {
                datastoreRepository.saveIsAuthenticated(authenticated)
            } catch (e: Exception) {
                Log.e("SettingsViewModel", "Error saving authentication state", e)
                // Optionally, notify UI of the error
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            authRepository.signOut()
            datastoreRepository.saveIsAuthenticated(false)
        }
    }
}


//@HiltViewModel
//class SettingsViewmodel @Inject constructor(
//    private val datastoreRepository: DatastoreRepository
//): ViewModel() {
//    private val _startDestination = MutableStateFlow<NavigationDestination>(NavigationDestination.SignIn)
//    val startDestination: StateFlow<NavigationDestination> get() = _startDestination
//
//    init {
//        viewModelScope.launch {
//            datastoreRepository.authenticated.collect{authenticated ->
//                _startDestination.value = if (authenticated) NavigationDestination.Home else NavigationDestination.SignIn
//            }
//        }
//    }
//
////    init {
////        viewModelScope.launch {
////            // Add try-catch here for robustness if DataStore operations can fail
////            try {
////                datastoreRepository.authenticated.collect { authenticated ->
////                    _startDestination.value = if (authenticated) NavigationDestination.Home else NavigationDestination.SignIn
////                }
////            } catch (e: Exception) {
////                Log.e("SettingsViewModel", "Error collecting authentication state", e)
////                // Handle error, perhaps default to SignIn
////                _startDestination.value = NavigationDestination.SignIn
////            }
////        }
////    }
//    val authenticated = datastoreRepository.authenticated.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.Eagerly,
//        initialValue = false
//    )
//
//    fun saveIsAuthenticated(authenticated: Boolean){
//        viewModelScope.launch {
//            datastoreRepository.saveIsAuthenticated(authenticated)
//        }
//    }
//}
