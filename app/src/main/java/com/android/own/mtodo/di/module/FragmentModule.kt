package com.android.own.mtodo.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.own.mtodo.base.BaseFragment
import com.android.own.mtodo.data.repository.ToDoRepository
import com.android.own.mtodo.ui.MainSharedViewModel
import com.android.own.mtodo.ui.todoadd.ToDoAddViewModel
import com.android.own.mtodo.ui.todoview.ToDoViewsViewModel
import com.android.own.mtodo.ui.todoview.adapter.ToDoListAdapter
import com.android.own.mtodo.utils.ViewModelProviderFactory
import com.android.own.mtodo.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.util.*

@Suppress("DEPRECATION")
@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)


    @Provides
    fun provideToDoListAdapter() = ToDoListAdapter(fragment.lifecycle, ArrayList())


    @Provides
    fun provideToDoViewsViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        toDoRepository: ToDoRepository
    ): ToDoViewsViewModel = ViewModelProviders.of(
        fragment.activity!!, ViewModelProviderFactory(ToDoViewsViewModel::class) {
            ToDoViewsViewModel(schedulerProvider, compositeDisposable, toDoRepository, ArrayList())
        }).get(ToDoViewsViewModel::class.java)

    @Provides
    fun provideToDoAddViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable, toDoRepository: ToDoRepository
    ): ToDoAddViewModel = ViewModelProviders.of(
        fragment.activity!!, ViewModelProviderFactory(ToDoAddViewModel::class) {
            ToDoAddViewModel(schedulerProvider, compositeDisposable, toDoRepository)
        }).get(ToDoAddViewModel::class.java)


    @Provides
    fun provideMainSharedViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
    ): MainSharedViewModel = ViewModelProviders.of(
        fragment.activity!!, ViewModelProviderFactory(MainSharedViewModel::class) {
            MainSharedViewModel(schedulerProvider, compositeDisposable)
        }).get(MainSharedViewModel::class.java)

}