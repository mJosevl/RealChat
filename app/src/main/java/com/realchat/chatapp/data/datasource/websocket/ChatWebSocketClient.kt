package com.sakhura.chatapp.data.datasource.websocket

import com.google.firebase.database.tubesock.WebSocket
import okhttp3.*
import okio.ByteString
import javax.inject.Inject

class ChatWebSocketClient @Inject constructor() {

    private var webSocket: WebSocket? = null
    private var listener: ChatWebSocketListener? = null

    fun connect(url: String, listener: ChatWebSocketListener) {
        this.listener = listener
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        webSocket = client.newWebSocket(request, listener)
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Cliente salió")
    }

    abstract class ChatWebSocketListener : WebSocketListener() {
        abstract fun onNuevoMensaje(mensaje: String)

        override fun onMessage(webSocket: WebSocket, text: String) {
            onNuevoMensaje(text)
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            t.printStackTrace()
        }
    }
}
