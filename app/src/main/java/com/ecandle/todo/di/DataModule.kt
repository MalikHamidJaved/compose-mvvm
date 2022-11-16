package com.ecandle.todo.di

import android.content.Context
import androidx.room.Room

import com.ecandle.todo.database.StreamDao
import com.ecandle.todo.database.StreamDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): StreamDatabase {
        return Room.databaseBuilder(
            context, StreamDatabase::class.java,
            StreamDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideStreamDAO(streamDatabase: StreamDatabase): StreamDao {
        return streamDatabase.streamDao()
    }
}