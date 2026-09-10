package com.example.miformacionctma.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.miformacionctma.data.local.AppDatabase
import com.example.miformacionctma.data.repository.PreferenciasRepository
import com.example.miformacionctma.data.repository.RoomReporteRepository
import com.example.miformacionctma.data.repository.UserPreferencesRepository
import com.example.miformacionctma.model.ReporteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

interface AppContainer {
    val reporteRepository: ReporteRepository
    val preferenciasRepository: PreferenciasRepository
    val dataStore: DataStore<Preferences>
}

class AppDataContainer(private val context: Context) : AppContainer {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override val dataStore: DataStore<Preferences>
        get() = context.dataStore

    override val reporteRepository: ReporteRepository by lazy {
        RoomReporteRepository(
            reporteDao = AppDatabase.getDatabase(context, applicationScope).reporteDao(),
            externalScope = applicationScope
        )
    }

    override val preferenciasRepository: PreferenciasRepository by lazy {
        UserPreferencesRepository(dataStore)
    }
}