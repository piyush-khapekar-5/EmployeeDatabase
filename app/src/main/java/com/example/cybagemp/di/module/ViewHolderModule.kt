package com.example.cybagemp.di.module

import androidx.lifecycle.LifecycleRegistry
import com.example.cybagemp.ui.base.BaseItemViewHolder
import com.mindorks.bootcamp.instagram.di.ViewModelScope
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}