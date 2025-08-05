package com.sakhura.chatapp.domain.repository

import com.sakhura.chatapp.domain.model.Sala

interface SalaRepository {
    suspend fun obtenerSalas(): List<Sala>
}
