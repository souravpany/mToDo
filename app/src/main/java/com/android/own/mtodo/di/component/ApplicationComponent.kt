package com.android.own.mtodo.di.component


import android.app.Application
import android.content.Context
import com.android.own.mtodo.MToDoApplication
import com.android.own.mtodo.data.local.db.DatabaseService
import com.android.own.mtodo.data.repository.ToDoRepository
import com.android.own.mtodo.di.ApplicationContext
import com.android.own.mtodo.di.module.ApplicationModule
import com.android.own.mtodo.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {


    fun inject(app: MToDoApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getDatabaseService(): DatabaseService

    fun getCompositeDisposable(): CompositeDisposable

    fun getSchedulerProvider(): SchedulerProvider


    fun getToDoRepository(): ToDoRepository
}
