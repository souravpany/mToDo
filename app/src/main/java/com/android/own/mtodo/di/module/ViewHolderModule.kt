package com.android.own.mtodo.di.module

import androidx.lifecycle.LifecycleRegistry
import com.android.own.mtodo.base.BaseItemViewHolder
import com.android.own.mtodo.di.ViewModelScope
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}