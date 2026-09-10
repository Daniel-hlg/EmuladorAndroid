package com.example.miformacionctma.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

interface PreferenciasRepository {
    val soloPendientes: Flow<Boolean>
    suspend fun guardarSoloPendientes(soloPendientes: Boolean)
}

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) : PreferenciasRepository {

    private object PreferencesKeys {
        val SOLO_PENDIENTES = booleanPreferencesKey("solo_pendientes")
    }

    override val soloPendientes: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.SOLO_PENDIENTES] ?: false
        }

    override suspend fun guardarSoloPendientes(soloPendientes: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SOLO_PENDIENTES] = soloPendientes
        }
    }
}