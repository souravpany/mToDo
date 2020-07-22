package com.android.own.mtodo

import android.app.Application
import com.android.own.mtodo.di.component.ApplicationComponent
import com.android.own.mtodo.di.component.DaggerApplicationComponent
import com.android.own.mtodo.di.module.ApplicationModule
import com.facebook.stetho.Stetho

/**
 *Stetho - for checking the data correctly adding into data base or not
 *
 * Run your app from Android Studio
 *
 *Visit chrome://inspect/#devices in your Chrome browser. Your app will be listed. Click the inspect link under its name and a new DevTools window will be launched.
 *
 * To view the database, expand “Web SQL” in the menu on the left and you’ll see your database listed.
 Click to expand to view the tables.
 *
 *
 * **/

class MToDoApplication : Application() {


    lateinit var applicationComponent: ApplicationComponent


    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
        injectDependencies()

    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }


}
