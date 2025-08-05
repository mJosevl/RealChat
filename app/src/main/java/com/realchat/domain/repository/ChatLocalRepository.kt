package com.realchat.domain.repository

import com.realchat.domain.model.EstadoMensaje
import com.realchat.domain.model.Mensaje

interface ChatLocalRepository {
    suspend fun obtenerMensajes(salaId: String): List<Mensaje>
    suspend fun guardarMensaje(mensaje: Mensaje, salaId: String)

    override suspend fun actualizarEstado(timestamp: Long, estado: EstadoMensaje) {
        dao.actualizarEstadoMensaje(timestamp, estado.name)
    }

}
