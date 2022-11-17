package com.ecandle.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StreamCacheEntity::class], version = 1)
abstract class StreamDatabase : RoomDatabase() {
    abstract fun streamDao(): TaskDao

    companion object {
        const val DATABASE_NAME: String = "streams_db"
    }
}