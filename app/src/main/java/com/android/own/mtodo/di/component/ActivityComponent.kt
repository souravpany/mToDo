package com.android.own.mtodo.di.component

import com.android.own.mtodo.di.ActivityScope
import com.android.own.mtodo.di.module.ActivityModule
import com.android.own.mtodo.ui.home.HomeActivity
import com.android.own.mtodo.ui.intro.IntroActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(introActivity: IntroActivity)

    fun inject(introActivity: HomeActivity)

}