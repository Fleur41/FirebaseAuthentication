package com.sam.firebaseauthentication.Settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.firebaseauthentication.datastore.DatastoreRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewmodel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
): ViewModel() {
    val authenticated = datastoreRepository.authenticated.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun saveIsAuthenticated(authenticated: Boolean){
        viewModelScope.launch {
            datastoreRepository.saveIsAuthenticated(authenticated)
        }
    }
}