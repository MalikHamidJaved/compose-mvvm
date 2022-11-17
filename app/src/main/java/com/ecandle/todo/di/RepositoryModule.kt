package com.ecandle.todo.di


import com.candle.streams_player_mvvm.network.TasksApi
import com.candle.streams_player_mvvm.network.TaskApiResponseMapper
import com.ecandle.todo.database.CacheMapper
import com.ecandle.todo.database.TaskDao
import com.ecandle.todo.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        taskDao: TaskDao,
        tasksApi: TasksApi,
        cacheMapper: CacheMapper,
        streamMapper: TaskApiResponseMapper
    ): TaskRepository {
        return TaskRepository(taskDao, tasksApi, cacheMapper, streamMapper)
    }
}