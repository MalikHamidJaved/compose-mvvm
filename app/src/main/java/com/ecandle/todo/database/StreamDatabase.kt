package com.ecandle.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ecandle.todo.database.StreamCacheEntity
import com.ecandle.todo.database.StreamDao

@Database(entities = [StreamCacheEntity::class], version = 1)
abstract class StreamDatabase : RoomDatabase() {
    abstract fun streamDao(): StreamDao

    companion object {
        const val DATABASE_NAME: String = "streams_db"
    }
}