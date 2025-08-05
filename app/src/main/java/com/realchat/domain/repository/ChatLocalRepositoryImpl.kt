package com.realchat.domain.repository

import com.realchat.data.database.MensajeEntity
import com.realchat.data.database.dao.MensajeDao
import com.realchat.domain.model.Mensaje
import com.realchat.domain.repository.ChatLocalRepository
import javax.inject.Inject

class ChatLocalRepositoryImpl @Inject constructor(
    private val dao: MensajeDao
) : ChatLocalRepository {

    override suspend fun obtenerMensajes(salaId: String): List<Mensaje> {
        return dao.getMensajesPorSala(salaId).map {
            Mensaje(it.contenido, it.remitente, it.timestamp)
        }
    }

    override suspend fun guardarMensaje(mensaje: Mensaje, salaId: String) {
        dao.insertarMensaje(
            MensajeEntity(
                contenido = mensaje.contenido,
                remitente = mensaje.remitente,
                timestamp = mensaje.timestamp,
                salaId = salaId
            )
        )
    }
}
