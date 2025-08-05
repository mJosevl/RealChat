package com.sakhura.chatapp.di

import com.sakhura.chatapp.data.datasource.remote.*
import com.sakhura.chatapp.data.datasource.websocket.ChatWebSocketClient
import com.sakhura.chatapp.data.repository.*
import com.sakhura.chatapp.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // 🔐 Autenticación
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(): AuthRemoteDataSource {
        return FakeAuthRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        remote: AuthRemoteDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(remote)
    }

    // 💬 Salas de chat
    @Provides
    @Singleton
    fun provideSalaRemoteDataSource(): SalaRemoteDataSource {
        return FakeSalaRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideSalaRepository(
        remote: SalaRemoteDataSource
    ): SalaRepository {
        return SalaRepositoryImpl(remote)
    }

    @Provides
    @Singleton
    fun provideChatWebSocketClient(): ChatWebSocketClient {
        return ChatWebSocketClient()
    }

    @Provides
    @Singleton
    fun provideChatRepository(client: ChatWebSocketClient): ChatRepository {
        return ChatRepositoryImpl(client)
    }

    @Provides
    @Singleton
    fun provideChatLocalRepository(dao: MensajeDao): ChatLocalRepository {
        return ChatLocalRepositoryImpl(dao)
    }


}
