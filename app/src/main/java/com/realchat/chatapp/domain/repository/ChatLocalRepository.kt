package com.sakhura.chatapp.domain.repository

import com.sakhura.chatapp.domain.model.EstadoMensaje
import com.sakhura.chatapp.domain.model.Mensaje

interface ChatLocalRepository {
    suspend fun obtenerMensajes(salaId: String): List<Mensaje>
    suspend fun guardarMensaje(mensaje: Mensaje, salaId: String)

    override suspend fun actualizarEstado(timestamp: Long, estado: EstadoMensaje) {
        dao.actualizarEstadoMensaje(timestamp, estado.name)
    }

}
