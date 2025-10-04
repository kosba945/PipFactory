package com.example.pipfactorymanagement.data.repository

import com.example.pipfactorymanagement.data.database.dao.UserDao
import com.example.pipfactorymanagement.data.model.User
import com.example.pipfactorymanagement.data.network.ApiService
import com.example.pipfactorymanagement.data.network.LoginRequest
import com.example.pipfactorymanagement.data.network.TokenManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val tokenManager: TokenManager
) {
    
    suspend fun login(username: String, password: String): Result<User> {
        return try {
            val response = apiService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                val loginResponse = response.body()!!
                tokenManager.saveToken(loginResponse.token)
                userDao.insertUser(loginResponse.user)
                Result.success(loginResponse.user)
            } else {
                Result.failure(Exception("Login failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun logout(): Result<Unit> {
        return try {
            val response = apiService.logout()
            tokenManager.clearToken()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getCurrentUser(): Flow<User?> {
        return kotlinx.coroutines.flow.flow {
            userDao.getAllActiveUsers().collect { users ->
                emit(users.firstOrNull())
            }
        }
    }
    
    fun isLoggedIn(): Boolean {
        return tokenManager.isLoggedIn()
    }
    
    suspend fun refreshToken(): Result<Unit> {
        return try {
            val refreshToken = tokenManager.getToken() // In real app, store refresh token separately
            val response = apiService.refreshToken(com.example.pipfactorymanagement.data.network.RefreshTokenRequest(refreshToken!!))
            if (response.isSuccessful) {
                val loginResponse = response.body()!!
                tokenManager.saveToken(loginResponse.token)
                Result.success(Unit)
            } else {
                Result.failure(Exception("Token refresh failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
