package com.example.realchat.di

import com.example.realchat.data.datasource.local.OfflineDataSource
import com.example.realchat.data.datasource.remote.AuthRemoteDataSource
import com.example.realchat.data.datasource.remote.ChatRoomRemoteDataSource
import com.example.realchat.data.datasource.remote.MessageRemoteDataSource
import com.example.realchat.data.datasource.websocket.WebSocketManager
import com.example.realchat.data.repository.AuthRepositoryImpl
import com.example.realchat.data.repository.ChatRoomRepositoryImpl
import com.example.realchat.data.repository.MessageRepositoryImpl
import com.example.realchat.domain.repository.AuthRepository
import com.example.realchat.domain.repository.ChatRoomRepository
import com.example.realchat.domain.repository.MessageRepository
import com.example.realchat.utils.EncryptionUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Módulo de Hilt que proporciona las implementaciones de los repositorios.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provee una instancia Singleton de [AuthRepository].
     * @param authRemoteDataSource Fuente de datos remota para autenticación.
     * @param offlineDataSource Fuente de datos local para modo offline.
     * @return Implementación de [AuthRepository].
     */
    @Provides
    @Singleton
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource,
        offlineDataSource: OfflineDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(authRemoteDataSource, offlineDataSource)
    }

    /**
     * Provee una instancia Singleton de [ChatRoomRepository].
     * @param chatRoomRemoteDataSource Fuente de datos remota para salas de chat.
     * @param offlineDataSource Fuente de datos local para modo offline.
     * @return Implementación de [ChatRoomRepository].
     */
    @Provides
    @Singleton
    fun provideChatRoomRepository(
        chatRoomRemoteDataSource: ChatRoomRemoteDataSource,
        offlineDataSource: OfflineDataSource
    ): ChatRoomRepository {
        return ChatRoomRepositoryImpl(chatRoomRemoteDataSource, offlineDataSource)
    }

    /**
     * Provee una instancia Singleton de [MessageRepository].
     * @param messageRemoteDataSource Fuente de datos remota para mensajes.
     * @param offlineDataSource Fuente de datos local para modo offline.
     * @param webSocketManager Gestor de WebSocket.
     * @return Implementación de [MessageRepository].
     */
    @Provides
    @Singleton
    fun provideMessageRepository(
        messageRemoteDataSource: MessageRemoteDataSource,
        offlineDataSource: OfflineDataSource,
        webSocketManager: WebSocketManager,
        encryptionUtils: EncryptionUtils
    ): MessageRepository {
        return MessageRepositoryImpl(
            messageRemoteDataSource,
            webSocketManager,
            offlineDataSource,
            encryptionUtils
        )
    }
}

