package com.candle.streams_player_mvvm.network

import com.ecandle.todo.network.StreamItemResponse
import retrofit2.http.GET

interface StreamApi {

    @GET("/history")
    suspend fun get(): List<StreamItemResponse>
}