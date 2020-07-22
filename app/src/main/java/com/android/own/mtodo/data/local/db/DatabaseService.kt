package com.android.own.mtodo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.own.mtodo.data.local.db.dao.ToDoDao
import com.android.own.mtodo.data.local.db.entity.ToDoEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        ToDoEntity::class

    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

}