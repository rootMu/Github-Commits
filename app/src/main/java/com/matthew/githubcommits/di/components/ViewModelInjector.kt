package com.matthew.githubcommits.di.components

import com.matthew.githubcommits.di.modules.NetworkModule
import com.matthew.githubcommits.modules.viewmodel.ListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified ListViewModel.
     * @param listViewModel ListViewModel in which to inject the dependencies
     */
    fun inject(listViewModel: ListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}