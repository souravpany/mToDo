package com.android.own.mtodo.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.android.own.mtodo.MToDoApplication
import com.android.own.mtodo.data.local.db.DatabaseService
import com.android.own.mtodo.di.ApplicationContext
import com.android.own.mtodo.utils.rx.RxSchedulerProvider
import com.android.own.mtodo.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MToDoApplication) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application


    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService = Room.databaseBuilder(
        application,
        DatabaseService::class.java,
        "mtodo-database-project-db"
    )
        .build()
}
