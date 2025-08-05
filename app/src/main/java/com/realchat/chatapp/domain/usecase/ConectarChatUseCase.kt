package com.sakhura.chatapp.domain.usecase

import com.sakhura.chatapp.domain.repository.ChatRepository

class ConectarChatUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    fun conectar(salaId: String, onMensaje: (String) -> Unit) {
        repo.conectar(salaId, onMensaje)
    }
}
