package com.sakhura.chatapp.domain.usecase

import com.sakhura.chatapp.domain.repository.ChatRepository
import javax.inject.Inject

class EnviarMensajeUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    fun enviar(mensaje: String) = repo.enviar(mensaje)
}
