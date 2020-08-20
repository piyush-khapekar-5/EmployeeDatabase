package com.example.cybagemp.di.component

import com.example.cybagemp.di.module.ViewHolderModule
import com.example.cybagemp.ui.main.employee.EmpItemViewHolder
import com.mindorks.bootcamp.instagram.di.ViewModelScope
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: EmpItemViewHolder)
}