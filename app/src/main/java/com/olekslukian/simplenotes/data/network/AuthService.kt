package com.olekslukian.simplenotes.data.network

import com.olekslukian.simplenotes.data.models.auth.request.ChangePasswordRequestDto
import com.olekslukian.simplenotes.data.models.auth.request.LoginRequestDto
import com.olekslukian.simplenotes.data.models.auth.request.RegisterRequestDto
import com.olekslukian.simplenotes.data.models.auth.response.TokensResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("/auth/register")
    suspend fun register(@Body registerDto : RegisterRequestDto) : Boolean

    @POST("/auth/login")
    suspend fun login(@Body loginDto : LoginRequestDto) : TokensResponseDto

    @GET("/auth/refresh-token")
    suspend fun refreshToken(@Query("refreshToken") refreshToken: String): TokensResponseDto

    @PATCH("/auth/change-password")
    suspend fun changePassword(@Body changePasswordRequestDto: ChangePasswordRequestDto): Boolean
}