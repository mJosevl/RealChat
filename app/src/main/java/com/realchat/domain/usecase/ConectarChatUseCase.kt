package com.realchat.domain.usecase

import com.realchat.domain.repository.ChatRepository

class ConectarChatUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    fun conectar(salaId: String, onMensaje: (String) -> Unit) {
        repo.conectar(salaId, onMensaje)
    }
}
