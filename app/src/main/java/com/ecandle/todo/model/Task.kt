package com.ecandle.todo.model

data class Task(
    val id: Int,
    val recording: String?,
    val timestamp: String?,
    val username_from: String?,
    val username_to: String?,
    var isPlaying: Boolean = false
)