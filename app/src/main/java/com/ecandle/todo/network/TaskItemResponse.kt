package com.ecandle.todo.network


import com.google.gson.annotations.SerializedName

class StreamSResponse:ArrayList<TaskItemResponse>()

data class TaskItemResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("recording")
    val recording: String?,

    @SerializedName("timestamp")
    val timestamp: String?,

    @SerializedName("username_from")
    val username_from: String?,

    @SerializedName("username_to")
    val username_to: String?
)