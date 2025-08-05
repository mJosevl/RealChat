package com.realchat.data.datasource.remote

interface AuthRemoteDataSource {
    suspend fun login(email: String, password: String): Boolean
}
