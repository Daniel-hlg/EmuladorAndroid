package com.example.miformacionctma.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.miformacionctma.data.local.AppDatabase
import com.example.miformacionctma.data.remote.RemoteActividadDataSource
import com.example.miformacionctma.data.remote.api.ActividadApiService
import com.example.miformacionctma.data.repository.PreferenciasRepository
import com.example.miformacionctma.data.repository.RoomReporteRepository
import com.example.miformacionctma.data.repository.UserPreferencesRepository
import com.example.miformacionctma.model.ReporteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

interface AppContainer {
    val reporteRepository: ReporteRepository
    val preferenciasRepository: PreferenciasRepository
    val dataStore: DataStore<Preferences>
}

class AppDataContainer(private val context: Context) : AppContainer {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.miformacionctma.example.com/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val apiService: ActividadApiService by lazy {
        retrofit.create(ActividadApiService::class.java)
    }

    private val remoteDataSource: RemoteActividadDataSource by lazy {
        RemoteActividadDataSource(apiService)
    }

    override val dataStore: DataStore<Preferences>
        get() = context.dataStore

    override val reporteRepository: ReporteRepository by lazy {
        RoomReporteRepository(
            reporteDao = AppDatabase.getDatabase(context, applicationScope).reporteDao(),
            remoteDataSource = remoteDataSource,
            externalScope = applicationScope
        )
    }

    override val preferenciasRepository: PreferenciasRepository by lazy {
        UserPreferencesRepository(dataStore)
    }
}