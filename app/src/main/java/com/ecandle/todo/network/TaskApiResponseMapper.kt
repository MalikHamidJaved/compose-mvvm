package com.candle.streams_player_mvvm.network


import com.ecandle.todo.model.Task
import com.ecandle.todo.network.TaskItemResponse
import com.ecandle.todo.util.EntityMapper
import javax.inject.Inject

class TaskApiResponseMapper
@Inject
constructor() : EntityMapper<TaskItemResponse, Task> {
    override fun mapFromEntity(entity: TaskItemResponse): Task {
        return Task(
            id = entity.id,
            recording = entity.recording,
            username_from = entity.username_from,
            timestamp = entity.timestamp,
            username_to = entity.username_to
        )
    }

    override fun mapToEntity(domainModel: Task): TaskItemResponse {
        return TaskItemResponse(
            id = domainModel.id,
            recording = domainModel.recording,
            username_from = domainModel.username_from,
            timestamp = domainModel.timestamp,
            username_to = domainModel.username_to
        )
    }

    fun mapFromEntityList(entities: List<TaskItemResponse>): List<Task> {
        return entities.map { mapFromEntity(it) }
    }

}
