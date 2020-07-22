package com.android.own.mtodo.data.model

data class ToDoItem(

    val id: Long,

    val title: String,

    val description: String
) {
    override fun toString(): String {
        return title
    }
}