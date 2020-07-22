package com.android.own.mtodo.data.local.db.dao

import androidx.room.*
import com.android.own.mtodo.data.local.db.entity.ToDoEntity
import com.android.own.mtodo.data.model.ToDoItem
import io.reactivex.Single

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(demoEntity: ToDoEntity): Single<Long>

    @Update
    fun update(demoEntity: ToDoEntity?): Single<Int>


    @Query("SELECT * FROM todo_table ORDER BY id DESC")
    fun getAllToDo(): Single<List<ToDoItem>>

    @Query("DELETE FROM todo_table")
    fun deleteAll()

    @Query("SELECT count(*) from todo_table")
    fun count(): Single<Int>

    @Query("DELETE FROM todo_table WHERE id = :id")
    fun deleteToDoById(id: Long): Single<Int>

}