package com.matthew.githubcommits.base

import androidx.lifecycle.ViewModel
import com.matthew.githubcommits.di.components.DaggerViewModelInjector
import com.matthew.githubcommits.di.components.ViewModelInjector
import com.matthew.githubcommits.di.modules.NetworkModule
import com.matthew.githubcommits.modules.viewmodel.ListViewModel

abstract class BaseViewModel: ViewModel(){

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ListViewModel -> injector.inject(this)
        }
    }
}