package com.ecandle.todo.database


import com.ecandle.todo.model.Task
import com.ecandle.todo.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<StreamCacheEntity, Task> {
    override fun mapFromEntity(entity: StreamCacheEntity): Task {
        return Task(
            id = entity.id,
            recording = entity.recording,
            username_from = entity.username_from,
            timestamp = entity.timestamp,
            username_to = entity.username_to
        )
    }

    override fun mapToEntity(domainModel: Task): StreamCacheEntity {
        return StreamCacheEntity(
            id = domainModel.id,
            recording = domainModel.recording,
            username_from = domainModel.username_from,
            timestamp = domainModel.timestamp,
            username_to = domainModel.username_to
        )
    }

    fun mapFromEntityList(entities: List<StreamCacheEntity>): List<Task> {
        return entities.map { mapFromEntity(it) }
    }

}