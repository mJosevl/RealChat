package com.sakhura.chatapp.domain.usecase

import com.sakhura.chatapp.domain.model.Sala
import com.sakhura.chatapp.domain.repository.SalaRepository
import javax.inject.Inject

class ObtenerSalasUseCase @Inject constructor(
    private val repository: SalaRepository
) {
    suspend operator fun invoke(): List<Sala> = repository.obtenerSalas()
}
