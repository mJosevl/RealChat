package com.realchat.domain.usecase


import com.realchat.domain.model.Sala
import com.realchat.domain.repository.SalaRepository
import javax.inject.Inject

class ObtenerSalasUseCase @Inject constructor(
    private val repository: SalaRepository
) {
    suspend operator fun invoke(): List<Sala> = repository.obtenerSalas()
}
