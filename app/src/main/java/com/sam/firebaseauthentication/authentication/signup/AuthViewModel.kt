package com.sam.firebaseauthentication.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.firebaseauthentication.authentication.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> get() = _authState.asStateFlow()

    fun  signUp(email: String, password: String){

        viewModelScope.launch(Dispatchers.IO){
            _authState.value = AuthState.Loading
            delay(5000)
            repository.signUp(
                email = email,
                password = password,
                onSuccess = {
                    _authState.value = AuthState.Success
                },
                onFailure = { exception ->
                    _authState.value = AuthState.Error(exception.message ?: "Unknown error")
                }
            )
        }
    }
    fun  signIn(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.signIn(email, password)
        }
    }
}

sealed interface AuthState{
    data object Initial: AuthState
    data object Loading: AuthState
    data object Success: AuthState
    data class Error(val message: String): AuthState

}