package com.realchat.domain.usecase


import com.realchat.domain.repository.ChatRepository
import javax.inject.Inject

class EnviarMensajeUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    fun enviar(mensaje: String) = repo.enviar(mensaje)
}
