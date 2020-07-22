package com.android.own.mtodo.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.own.mtodo.base.BaseActivity
import com.android.own.mtodo.ui.MainSharedViewModel
import com.android.own.mtodo.ui.home.HomeViewModel
import com.android.own.mtodo.ui.intro.IntroViewModel
import com.android.own.mtodo.utils.ViewModelProviderFactory
import com.android.own.mtodo.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */

@Suppress("DEPRECATION")
@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)


    @Provides
    fun provideIntroViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
    ): IntroViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(IntroViewModel::class) {
            IntroViewModel(schedulerProvider, compositeDisposable)
        }).get(IntroViewModel::class.java)

    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
    ): HomeViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(schedulerProvider, compositeDisposable)
        }).get(HomeViewModel::class.java)


    @Provides
    fun provideMainSharedViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
    ): MainSharedViewModel = ViewModelProviders.of(
        activity!!, ViewModelProviderFactory(MainSharedViewModel::class) {
            MainSharedViewModel(schedulerProvider, compositeDisposable)
        }).get(MainSharedViewModel::class.java)

}