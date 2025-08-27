package com.olekslukian.simplenotes.data.network

import android.util.Log
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor  @Inject constructor(
    private val tokenManager: TokenManager,
    private val authService: AuthService
) : Interceptor{
    private val mutex = Mutex()

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (originalRequest.url.encodedPath.contains("/auth")) {
            return chain.proceed(originalRequest)
        }

        return runBlocking {
            val accessToken = tokenManager.getAccessToken()

            val requestWithToken = if (accessToken != null) {
                originalRequest.newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .build()
            } else {
                originalRequest
            }

            val response = chain.proceed(requestWithToken)

            if (response.code == 401) {
                response.close()
                handleTokenRefresh(chain, originalRequest)
            } else {
                response
            }
        }
    }

    private suspend fun handleTokenRefresh(
        chain: Interceptor.Chain,
        originalRequest: Request
    ) : Response {
        return mutex.withLock {
            val refreshToken =
                tokenManager.getRefreshToken() ?: return@withLock chain.proceed(originalRequest)

            val currentToken = tokenManager.getAccessToken()

            if (currentToken != null && !tokenManager.isTokenExpired(currentToken)) {
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $currentToken")
                    .build()
                return@withLock chain.proceed(newRequest)
            }

            try {
                val tokensResponse = authService.refreshToken(refreshToken)
                val accessToken = tokensResponse.accessToken
                val newRefreshToken = tokensResponse.refreshToken

                if (accessToken != null && newRefreshToken != null) {
                    tokenManager.saveTokens(accessToken, newRefreshToken)

                    val newRequest = originalRequest.newBuilder()
                        .header("Authorization", "Bearer $accessToken")
                        .build()
                    chain.proceed(newRequest)
                } else {
                    tokenManager.clearTokens()
                    chain.proceed(originalRequest)
                }
            } catch(e: Exception) {
                Log.e("AuthInterceptor", "Token refresh failed", e)
                tokenManager.clearTokens()
                chain.proceed(originalRequest)
            }
        }
    }
}