package com.android.own.mtodo.ui

import androidx.lifecycle.MutableLiveData
import com.android.own.mtodo.base.BaseViewModel
import com.android.own.mtodo.data.model.ToDoItem
import com.android.own.mtodo.utils.Event
import com.android.own.mtodo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainSharedViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    override fun onCreate() {}

    val homeRedirection = MutableLiveData<Event<Boolean>>()

    val homeRedirectionFromView = MutableLiveData<Event<Boolean>>()


    val newToDo: MutableLiveData<Boolean> = MutableLiveData()

    val clearField: MutableLiveData<Boolean> = MutableLiveData()

    val updateToDo: MutableLiveData<ToDoItem> = MutableLiveData()

    fun onHomeRedirect() {
        homeRedirection.postValue(Event(true))
    }

    fun onHomeRedirectFromView() {
        homeRedirectionFromView.postValue(Event(true))
    }

    fun onUpdateToDo(toDoItem: ToDoItem) {
        updateToDo.postValue(toDoItem)
    }

    fun onClearToDoField() {
        clearField.postValue(true)
    }
}