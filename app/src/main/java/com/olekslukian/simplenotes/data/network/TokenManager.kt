package com.olekslukian.simplenotes.data.network

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.auth0.jwt.JWT
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(private val dataStore: DataStore<Preferences>)  {
    private val accessTokenKey = stringPreferencesKey("access_token")
    private val refreshTokenKey = stringPreferencesKey("refresh_token")

    suspend fun getAccessToken() : String? {
        return dataStore.data.map {
            preferences -> preferences[accessTokenKey]
        }.first()
    }

    suspend fun getRefreshToken() : String? {
        return dataStore.data.map {
            preferences -> preferences[refreshTokenKey]
        }.first()
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[accessTokenKey] = accessToken
            preferences[refreshTokenKey] = refreshToken
        }
    }

    suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.remove(accessTokenKey)
            preferences.remove(refreshTokenKey)
        }
    }

    suspend fun isAuthenticated(): Boolean {
        val token = getAccessToken()
        return token != null && !isTokenExpired(token)
    }

    fun isTokenExpired(token: String): Boolean {
        return try {
            val jwt = JWT.decode(token)
            jwt.expiresAt?.before(Date()) == true
        } catch (e: Exception) {
            true
        }
    }
}