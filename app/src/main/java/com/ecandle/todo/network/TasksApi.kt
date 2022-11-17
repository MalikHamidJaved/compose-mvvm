package com.candle.streams_player_mvvm.network

import com.ecandle.todo.network.TaskItemResponse
import retrofit2.http.GET

interface TasksApi {

    @GET("/history")
    suspend fun get(): List<TaskItemResponse>
}