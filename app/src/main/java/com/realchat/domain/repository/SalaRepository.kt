package com.realchat.domain.repository


import com.realchat.domain.model.Sala

interface SalaRepository {
    suspend fun obtenerSalas(): List<Sala>
}
