
package com.realchat.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
}

