package com.ecandle.todo.repository


import com.candle.streams_player_mvvm.network.TasksApi
import com.candle.streams_player_mvvm.network.TaskApiResponseMapper
import com.ecandle.todo.database.CacheMapper
import com.ecandle.todo.database.TaskDao
import com.ecandle.todo.model.LoggedInUser
import com.ecandle.todo.model.Task
import com.ecandle.todo.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskRepository
constructor(
    private val taskDao: TaskDao,
    private val tasksApi: TasksApi,
    private val cacheMapper: CacheMapper,
    private val taskApiResponseMapper: TaskApiResponseMapper
) {
    suspend fun getTask(user: LoggedInUser): Flow<DataState<List<Task>>> = flow {
        emit(DataState.Loading)
        try {
            val networkStream = tasksApi.get()
            if(networkStream.size > 0){
                val streams = taskApiResponseMapper.mapFromEntityList(networkStream)
                for (stream in streams) {
                    taskDao.insert(cacheMapper.mapToEntity(stream))
                }

            }

            if(user.isAdmin){
                val cachedStream = taskDao.get()
                emit(DataState.Success(cacheMapper.mapFromEntityList(cachedStream)))
            }else{
                val cachedStream = taskDao.getForFilteredUser(user.userId)
                emit(DataState.Success(cacheMapper.mapFromEntityList(cachedStream)))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getTaskFromLocal(query: String, userData: LoggedInUser): Flow<DataState<List<Task>>> = flow {
        emit(DataState.Loading)
        try {


            if(userData.isAdmin){
                var cachedStream = taskDao.get(query)
                emit(DataState.Success(cacheMapper.mapFromEntityList(cachedStream)))
            }else{
                var cachedStream = taskDao.getForFilteredUser(query,userData.userId)
                emit(DataState.Success(cacheMapper.mapFromEntityList(cachedStream)))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}