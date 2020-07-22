package com.android.own.mtodo.ui.todoview.adapter

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.android.own.mtodo.base.BaseAdapter
import com.android.own.mtodo.data.model.ToDoItem

class ToDoListAdapter(
        parentLifecycle: Lifecycle,
        toDoItem: ArrayList<ToDoItem>
) : BaseAdapter<ToDoItem, ToDoItemViewHolder>(parentLifecycle, toDoItem) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ToDoItemViewHolder(parent)

}