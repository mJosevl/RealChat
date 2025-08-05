package com.sakhura.chatapp.di

import android.content.Context
import androidx.room.Room
import com.sakhura.chatapp.data.database.ChatDatabase
import com.sakhura.chatapp.data.database.dao.MensajeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): ChatDatabase =
        Room.databaseBuilder(appContext, ChatDatabase::class.java, "chat_db").build()

    @Provides
    fun provideMensajeDao(db: ChatDatabase): MensajeDao = db.mensajeDao()
}
