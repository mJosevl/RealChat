package com.sakhura.chatapp.data.repository

import com.sakhura.chatapp.data.datasource.remote.SalaRemoteDataSource
import com.sakhura.chatapp.domain.model.Sala
import com.sakhura.chatapp.domain.repository.SalaRepository
import javax.inject.Inject

class SalaRepositoryImpl @Inject constructor(
    private val remote: SalaRemoteDataSource
) : SalaRepository {
    override suspend fun obtenerSalas(): List<Sala> {
        return remote.obtenerSalas()
    }
}
