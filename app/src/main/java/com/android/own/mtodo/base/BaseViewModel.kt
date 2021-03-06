package com.android.own.mtodo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.own.mtodo.utils.Resource
import com.android.own.mtodo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable
    ) : ViewModel() {

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    abstract fun onCreate()
}