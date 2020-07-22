package com.android.own.mtodo.ui.home

import androidx.lifecycle.MutableLiveData
import com.android.own.mtodo.base.BaseViewModel
import com.android.own.mtodo.utils.Event
import com.android.own.mtodo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Sourav K.P on
 *
 * 19, June, 2020
 *
 **/

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(
    schedulerProvider, compositeDisposable
) {

    val todoViewFragment = MutableLiveData<Event<Boolean>>()
    val todoAddFragment = MutableLiveData<Event<Boolean>>()

    override fun onCreate() {
        todoViewFragment.postValue(Event(true))
    }

    fun onToDoAddClicked() {
        todoAddFragment.postValue(Event(true))
    }

    fun onToDoViewClicked() {
        todoViewFragment.postValue(Event(true))
    }
}