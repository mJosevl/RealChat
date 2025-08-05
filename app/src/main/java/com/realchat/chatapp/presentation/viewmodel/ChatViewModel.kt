package com.sakhura.chatapp.presentation.viewmodel

import androidx.lifecycle.*
import com.sakhura.chatapp.domain.usecase.ConectarChatUseCase
import com.sakhura.chatapp.domain.usecase.EnviarMensajeUseCase
import com.sakhura.chatapp.domain.repository.ChatLocalRepository
import com.sakhura.chatapp.domain.model.Mensaje
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val conectar: ConectarChatUseCase,
    private val enviar: EnviarMensajeUseCase,
    private val chatLocal: ChatLocalRepository
) : ViewModel() {

    private val _mensajes = MutableLiveData<MutableList<String>>(mutableListOf())
    val mensajes: LiveData<MutableList<String>> = _mensajes

    fun conectarWebSocket(salaId: String) {
        viewModelScope.launch {
            cargarMensajesLocalmente(salaId)

            conectar.conectar(salaId) { nuevoMensaje ->
                viewModelScope.launch {
                    _mensajes.value?.add(nuevoMensaje)
                    _mensajes.postValue(_mensajes.value)

                    chatLocal.guardarMensaje(
                        Mensaje(
                            contenido = nuevoMensaje,
                            remitente = "otro",
                            timestamp = System.currentTimeMillis()
                        ),
                        salaId
                    )
                }
            }
        }
    }

    fun enviarMensaje(texto: String) {
        enviar.enviar(texto)
        val mensaje = "Yo: $texto"
        _mensajes.value?.add(mensaje)
        _mensajes.postValue(_mensajes.value)
    }

    private suspend fun cargarMensajesLocalmente(salaId: String) {
        try {
            val mensajes = chatLocal.obtenerMensajes(salaId)
            _mensajes.postValue(mensajes.map {
                "${it.remitente}: ${it.contenido} (${it.estado.name.lowercase()})"
            }.toMutableList())
        } catch (e: Exception) {
            _mensajes.postValue(mutableListOf())
        }
    }
}