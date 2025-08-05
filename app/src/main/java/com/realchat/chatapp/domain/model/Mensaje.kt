package com.sakhura.chatapp.domain.model

enum class EstadoMensaje {
    ENVIADO, ENTREGADO, LEIDO
}

data class Mensaje(
    val contenido: String,
    val remitente: String,
    val timestamp: Long,
    val estado: EstadoMensaje = EstadoMensaje.ENVIADO
)