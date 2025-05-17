package com.sam.firebaseauthentication.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "my_preference")

class DatastoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val authenticatedKey = booleanPreferencesKey(name = "authenticated")

    val authenticated: Flow<Boolean> = context.dataStore.data.map { preference ->
        preference[authenticatedKey] ?: false
    }

    suspend fun saveIsAuthenticated(authenticated: Boolean) {
        context.dataStore.edit { preference ->
            preference[authenticatedKey] = authenticated
        }
    }
}