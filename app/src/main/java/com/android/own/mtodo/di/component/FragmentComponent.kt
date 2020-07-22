package com.android.own.mtodo.di.component


import com.android.own.mtodo.di.FragmentScope
import com.android.own.mtodo.di.module.FragmentModule
import com.android.own.mtodo.ui.todoadd.ToDoAddFragment
import com.android.own.mtodo.ui.todoview.ToDoViewFragment
import dagger.Component

@FragmentScope
@Component(
        dependencies = [ApplicationComponent::class],
        modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: ToDoViewFragment)
    
    fun inject(fragment: ToDoAddFragment)


}