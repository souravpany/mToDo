package com.android.own.mtodo.data.repository

import com.android.own.mtodo.data.local.db.DatabaseService
import com.android.own.mtodo.data.local.db.entity.ToDoEntity
import io.reactivex.Single
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val databaseService: DatabaseService
) {


    fun deleteToDo(id: Long) = databaseService.toDoDao().deleteToDoById(id)


    fun returnAllToDo() = databaseService.toDoDao().getAllToDo()


    fun addToDoDataToDatabase(title: String, description: String): Single<Long> =
        databaseService.toDoDao()
            .insert(
                ToDoEntity(
                    title = title,
                    description = description
                )
            )


    fun updateToDo(id: Long, title: String, description: String): Single<Int> =
        databaseService.toDoDao().update(
            ToDoEntity(
                id = id,
                title = title,
                description = description
            )
        )
}