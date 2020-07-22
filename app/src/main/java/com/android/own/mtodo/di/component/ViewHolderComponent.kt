package com.android.own.mtodo.di.component


import com.android.own.mtodo.di.ViewModelScope
import com.android.own.mtodo.di.module.ViewHolderModule
import com.android.own.mtodo.ui.todoview.adapter.ToDoItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
        dependencies = [ApplicationComponent::class],
        modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: ToDoItemViewHolder)
}