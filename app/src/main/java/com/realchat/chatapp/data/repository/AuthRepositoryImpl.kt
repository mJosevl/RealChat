package com.sakhura.chatapp.data.repository


import com.sakhura.chatapp.data.datasource.remote.AuthRemoteDataSource
import com.sakhura.chatapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun login(email: String, password: String): Boolean {
        return remote.login(email, password)
    }
}
