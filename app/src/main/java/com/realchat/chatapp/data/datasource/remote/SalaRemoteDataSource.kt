package com.sakhura.chatapp.data.datasource.remote

import com.sakhura.chatapp.domain.model.Sala

interface SalaRemoteDataSource {
    suspend fun obtenerSalas(): List<Sala>
}
