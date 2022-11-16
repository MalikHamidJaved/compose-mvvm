package com.ecandle.todo.di


import com.candle.streams_player_mvvm.network.StreamApi
import com.candle.streams_player_mvvm.network.StreamApiResponseMapper
import com.ecandle.todo.database.CacheMapper
import com.ecandle.todo.database.StreamDao
import com.ecandle.todo.repository.StreamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        streamDao: StreamDao,
        streamApi: StreamApi,
        cacheMapper: CacheMapper,
        streamMapper: StreamApiResponseMapper
    ): StreamRepository {
        return StreamRepository(streamDao, streamApi, cacheMapper, streamMapper)
    }
}