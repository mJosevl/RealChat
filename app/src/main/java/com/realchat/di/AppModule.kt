package com.realchat.di

import com.realchat.data.datasource.remote.*
import com.realchat.data.datasource.local.MensajeDao
import com.realchat.data.datasource.remote.FakeAuthRemoteDataSour
import com.realchat.data.datasource.websocket.ChatWebSocketClient
import com.realchat.data.repository.*
import com.realchat.domain.repository.*
import com.realchat.data.datasource.remote.FakeSalaRemoteDataSource
import com.realchat.data.repository.AuthRepositoryImpl
import com.realchat.data.repository.ChatRepositoryImpl
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
