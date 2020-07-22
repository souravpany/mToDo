package com.android.own.mtodo.ui.todoview.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.own.mtodo.base.BaseItemViewModel
import com.android.own.mtodo.data.model.ToDoItem
import com.android.own.mtodo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ToDoItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseItemViewModel<ToDoItem>(schedulerProvider, compositeDisposable) {


    val title: LiveData<String> = Transformations.map(data) {
        it.title
    }

    val description: LiveData<String> = Transformations.map(data) {
        it.description
    }

    val id: LiveData<Long> = Transformations.map(data) {
        it.id
    }


    override fun onCreate() {}
}