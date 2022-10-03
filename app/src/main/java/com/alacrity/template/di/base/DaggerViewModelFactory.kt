package com.alacrity.template.di.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class DaggerViewModelFactory
@Inject constructor(
    private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = creators[modelClass]
            ?: throw IllegalArgumentException("Unknown view model class: $modelClass")
        @Suppress("UNCHECKED_CAST")
        return viewModelProvider.get() as T
    }
}