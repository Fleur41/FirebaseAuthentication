package com.sam.firebaseauthentication.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.firebaseauthentication.authentication.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    fun  signUp(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.signUp(email, password)
        }
    }
    fun  signIn(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.signIn(email, password)
        }
    }
}