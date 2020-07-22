package com.android.own.mtodo.ui.intro

import androidx.lifecycle.MutableLiveData
import com.android.own.mtodo.base.BaseViewModel
import com.android.own.mtodo.utils.Event
import com.android.own.mtodo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class IntroViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(
    schedulerProvider, compositeDisposable
) {

    val launchHomeScreen: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()


    fun lunchMainScreen() {
        launchHomeScreen.postValue(Event(emptyMap()))
    }


    override fun onCreate() {}
}